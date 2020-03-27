package ua.turskyi.visitedcountries.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
//    private val countriesUseCase: GetCountriesUseCase
) : BaseViewModel() {

//    val countriesLiveData = MutableLiveData<List<Country>>()

    private val _navigateToAllCountries = MutableLiveData<Boolean>()
    val navigateToAllCountries: LiveData<Boolean>
        get() = _navigateToAllCountries

//    init {
//        getCountries()
//    }

    fun onFloatBtnClicked() {
        _navigateToAllCountries.value = true
    }

    fun onNavigatedToAllCountries() {
        _navigateToAllCountries.value = false
    }

//    private fun getCountries() {
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
//    }
}