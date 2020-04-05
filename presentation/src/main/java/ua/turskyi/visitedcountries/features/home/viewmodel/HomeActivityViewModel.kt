package ua.turskyi.visitedcountries.features.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.usecases.*
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import ua.turskyi.visitedcountries.features.home.extentions.isOnline
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    application: Application,
    private val removeFromVisitedUseCase: RemoveFromVisitedUseCase,
    private val getNotVisitedNumUseCase: GetNotVisitedNumUseCase,
    private val getVisitedCountriesUseCase: GetVisitedCountriesUseCase,
    private val getCountriesFromApiUseCase: GetCountriesFromApiUseCase,
    private val addCountriesToDbUseCase: AddCountriesToDbUseCase
) : BaseViewModel(application) {

    private val _visitedCountriesFromRxDB = MutableLiveData<List<Country>>()
    var visitedCountriesFromRxDB: MutableLiveData<List<Country>>
        get() = _visitedCountriesFromRxDB

    private val _navigateToAllCountries = MutableLiveData<Boolean>()
    val navigateToAllCountries: LiveData<Boolean>
        get() = _navigateToAllCountries

    var notVisitedCountRxDB = 0

    init {
        visitedCountriesFromRxDB = _visitedCountriesFromRxDB
        viewModelScope.launch {
            when {
                isOnline() -> {
                    refreshCountriesInDb()
                }
            }
        }
    }

    fun onFloatBtnClicked() {
        _navigateToAllCountries.value = true
    }

    fun onNavigatedToAllCountries() {
        _navigateToAllCountries.value = false
    }

    private fun getVisitedCountriesFromDB() {
        val disposable = getVisitedCountriesUseCase.execute(
            Consumer { countries: List<Country> ->
                _visitedCountriesFromRxDB.postValue(countries)
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
    }

    private fun refreshCountriesInDb() {
        val disposable = getCountriesFromApiUseCase.execute(
            Consumer { countries: List<Country> ->
                addCountriesToDbUseCase.execute(countries)
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
    }

    fun getNotVisitedCountFromDB() {
        val disposable = getNotVisitedNumUseCase.execute(
            Consumer {
                getVisitedCountriesFromDB()
            },
            Consumer { notVisitedCountries ->
                notVisitedCountRxDB = notVisitedCountries
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
    }

    fun removeFromVisited(country: Country) {
        val disposable = Observable.just(country)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ removedCountry ->
                removeFromVisitedUseCase.execute(removedCountry)
                getVisitedCountriesFromDB()
            }, { throwable ->
                Log.d(throwable.message, "error :(")
            })
        disposable?.let { compositeDisposable.add(it) }
    }
}