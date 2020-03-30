package ua.turskyi.visitedcountries.features.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.turskyi.data.api.Constant.API_BASE_URL
import ua.turskyi.data.api.service.CountriesApiService
import ua.turskyi.data.db.CountriesDataBase
import ua.turskyi.data.repositories.CountriesRepository
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.usecase.GetCountriesUseCase
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import ua.turskyi.visitedcountries.features.home.extentions.isOnline
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    application: Application,
    private val countriesUseCase: GetCountriesUseCase
) : BaseViewModel(application) {

    private val _visitedCountriesFromRxDB = MutableLiveData<List<Country>>()
    val visitedCountriesFromRxDB: MutableLiveData<List<Country>>
        get() = _visitedCountriesFromRxDB

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since viewModelJob is passed, all coroutines launched by uiScope can be canceled by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = CountriesDataBase.getInstance(application)
    private val countriesRepository = database?.let {
        CountriesRepository(it, countriesUseCase, compositeDisposable)
    }
    private val _navigateToAllCountries = MutableLiveData<Boolean>()
    val navigateToAllCountries: LiveData<Boolean>
        get() = _navigateToAllCountries
    var notVisitedCountRxDB = 0
    init {
        viewModelScope.launch {
            when {isOnline() -> countriesRepository?.refreshCountries() }
        }
    }

    fun onFloatBtnClicked() {
        _navigateToAllCountries.value = true
    }

    fun onNavigatedToAllCountries() {
        _navigateToAllCountries.value = false
    }

    private fun getVisitedCountriesFromDB() {
        val visitedDisposable = database?.countryDAO()?.getVisitedRxLiveAll()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.subscribe({ visitedCountries ->
                visitedCountriesFromRxDB.postValue(visitedCountries)
            }, { throwable ->
                Log.d(throwable.message, "error :(")
            })
        visitedDisposable?.let { compositeDisposable.add(it) }
    }

    fun getNotVisitedCountriesFromDB() {
        val notVisitedDisposable = database?.countryDAO()?.getCountNotVisitedRx()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.doOnSubscribe {
                getVisitedCountriesFromDB()
            }
            ?.subscribe({ notVisitedCountries ->
                notVisitedCountRxDB = notVisitedCountries
            }, { throwable ->
                Log.d(throwable.message, "error :(")
            })
        notVisitedDisposable?.let { compositeDisposable.add(it) }
    }

    fun removeFromVisited(country: Country) {
        val disposable = Observable.just(country)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ removedCountry ->
                database?.countryDAO()?.insert(removedCountry)
            }, { throwable ->
                Log.d(throwable.message, "error :(")
            })
        disposable?.let { compositeDisposable.add(it) }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}