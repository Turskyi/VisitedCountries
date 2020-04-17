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
import ua.turskyi.visitedcountries.utils.isOnline
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    application: Application,
    private val removeFromVisitedUseCase: RemoveFromVisitedUseCase,
    private val getNotVisitedNumUseCase: GetNotVisitedNumUseCase,
    private val getVisitedCountriesUseCase: GetVisitedCountriesUseCase,
    private val getCountriesFromApiUseCase: GetCountriesFromApiUseCase,
    private val addCountriesToDbUseCase: AddCountriesToDbUseCase
) : BaseViewModel(application) {

    var notVisitedCount = 0

    private val _visitedCountries = MutableLiveData<List<Country>>()
    var visitedCountries: MutableLiveData<List<Country>>

    private val _navigateToAllCountries = MutableLiveData<Boolean>()
    val navigateToAllCountries: LiveData<Boolean>
        get() = _navigateToAllCountries

    init {
        visitedCountries = _visitedCountries
        viewModelScope.launch {
            when {
                isOnline() -> refreshCountriesInDb()
            }
        }
    }

    private fun refreshCountriesInDb() {
        val disposable = getCountriesFromApiUseCase.execute(
            Consumer { countries: List<Country> ->
                addCountriesToDbUseCase.execute(countries)
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
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
                _visitedCountries.postValue(countries)
            },
            Consumer { Log.d(it, "error :(") })
        compositeDisposable.add(disposable)
    }

    fun getNotVisitedCountFromDB() {
        val disposable = getNotVisitedNumUseCase.execute(
            Consumer {
                getVisitedCountriesFromDB()
            },
            Consumer { notVisitedCountriesNum ->
                notVisitedCount = notVisitedCountriesNum
            },
            Consumer {
                Log.d(it, "error :(")
            })
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