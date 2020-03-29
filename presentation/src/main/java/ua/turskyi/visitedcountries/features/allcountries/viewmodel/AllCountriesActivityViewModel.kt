package ua.turskyi.visitedcountries.features.allcountries.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.usecase.GetCountriesUseCase
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import ua.turskyi.visitedcountries.features.allcountries.view.adapter.CountriesPositionalDataSource
import ua.turskyi.visitedcountries.utils.MainThreadExecutor
import java.util.concurrent.Executors
import javax.inject.Inject

class AllCountriesActivityViewModel @Inject constructor(application: Application,
    private val countriesUseCase: GetCountriesUseCase
) : BaseViewModel(application) {

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
    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: MutableLiveData<List<Country>>
        get() = _countriesLiveData

    var pagedList: PagedList<Country>

    init {
        val dataSource = CountriesPositionalDataSource(application, countriesUseCase, compositeDisposable)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        pagedList = PagedList.Builder(dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()

        viewModelScope.launch { getCountries() }
    }

    private fun getCountries() {
        val disposable = countriesUseCase.execute(
            Consumer { countries: List<Country> ->
                _countriesLiveData.postValue(countries)
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
    }
    fun markAsVisited(country: Country) {
        val disposable = Observable.just(country)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ newCountry ->
//                database?.countryDAO()?.insert(newCountry)
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