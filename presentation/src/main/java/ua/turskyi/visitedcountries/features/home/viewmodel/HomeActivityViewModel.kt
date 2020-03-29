package ua.turskyi.visitedcountries.features.home.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ua.turskyi.domain.models.Country
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor( application: Application
//    private val countriesUseCase: GetCountriesUseCase
) : BaseViewModel(application) {

    val countriesLiveData = MutableLiveData<List<Country>>()

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _navigateToAllCountries = MutableLiveData<Boolean>()
    val navigateToAllCountries: LiveData<Boolean>
        get() = _navigateToAllCountries

    init {
        getCountries()
    }

    fun onFloatBtnClicked() {
        _navigateToAllCountries.value = true
    }

    fun onNavigatedToAllCountries() {
        _navigateToAllCountries.value = false
    }

    private fun getCountries() {
//        val disposable = countriesUseCase.execute(
//            Consumer { countries: List<Country> ->
//                countriesLiveData.postValue(countries)
//            },
//            Consumer {
//                if (it != null) {
//
//                }
//            })
//        compositeDisposable.add(disposable)
    }

    fun removeFromVisited(country: Country) {
//        val disposable = Observable.just(country)
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())
//            .subscribe({ removedCountry ->
//                database?.countryDAO()?.insert(removedCountry)
//            }, { throwable ->
//                Timber.d(throwable, "error :(")
//            })
//        disposable?.let { compositeDisposable.add(it) }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}