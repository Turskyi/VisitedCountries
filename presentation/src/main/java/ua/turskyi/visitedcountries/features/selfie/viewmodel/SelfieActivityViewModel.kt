package ua.turskyi.visitedcountries.features.selfie.viewmodel

import android.app.Application
import ua.turskyi.visitedcountries.common.ui.base.BaseViewModel
import javax.inject.Inject

class SelfieActivityViewModel @Inject constructor( application: Application
//    private val countriesUseCase: GetCountriesUseCaseImpl
) : BaseViewModel(application) {

//    val countriesLiveData = MutableLiveData<List<Country>>()

//    init {
//        getCountries()
//    }
//
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