package ua.turskyi.visitedcountries.features.allcountries.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.usecases.GetCountriesByRangeUseCase
import ua.turskyi.domain.usecases.GetCountriesFromDbUseCase
import ua.turskyi.domain.usecases.MarkAsVisitedUseCase
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import ua.turskyi.visitedcountries.features.allcountries.view.adapter.CountriesPositionalDataSource
import ua.turskyi.visitedcountries.utils.MainThreadExecutor
import java.util.concurrent.Executors
import javax.inject.Inject

class AllCountriesActivityViewModel
@Inject constructor(
    application: Application,
    private val getCountriesFromDbUseCase: GetCountriesFromDbUseCase,
    getCountriesByRangeUseCase: GetCountriesByRangeUseCase,
    private val markAsVisitedUseCase: MarkAsVisitedUseCase
) : BaseViewModel(application) {

    private val _countriesLiveData = MutableLiveData<List<Country>>()
    val countriesLiveData: MutableLiveData<List<Country>>
        get() = _countriesLiveData

    private val _visibilityLoader = MutableLiveData<Int>()
    val visibilityLoader: MutableLiveData<Int>
        get() = _visibilityLoader

    var pagedList: PagedList<Country>

    init {
        val dataSource = CountriesPositionalDataSource(getCountriesByRangeUseCase, compositeDisposable)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        pagedList = PagedList.Builder(dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()

        viewModelScope.launch {
            _visibilityLoader.postValue(View.VISIBLE)
            getCountriesFromDb()
        }
    }

    private fun getCountriesFromDb() {
        val disposable = getCountriesFromDbUseCase.execute(
            Consumer { countries: List<Country> ->
                _countriesLiveData.postValue(countries)
                _visibilityLoader.postValue(View.GONE)
            },
            Consumer {
                Log.d(it, "error :(")
                _visibilityLoader.postValue(View.GONE)
            })
        compositeDisposable.add(disposable)
    }

    fun markAsVisited(country: Country) {
        val disposable = Observable.just(country)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ newCountry ->
                markAsVisitedUseCase.execute(newCountry)
            }, { throwable ->
                Log.d(throwable.message, "error :(")
            })
        disposable?.let { compositeDisposable.add(it) }
    }
}