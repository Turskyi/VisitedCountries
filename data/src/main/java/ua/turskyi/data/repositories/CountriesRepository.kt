package ua.turskyi.data.repositories

import android.util.Log
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.turskyi.data.api.modelresponse.CountryListResponse
import ua.turskyi.data.api.service.CountriesApiService
import ua.turskyi.data.db.CountriesDataBase
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.usecase.GetCountriesUseCase

class CountriesRepository(
    private val database: CountriesDataBase,
    private val countriesUseCase: GetCountriesUseCase,
    private val compositeDisposable: CompositeDisposable
) {

     fun getCountriesFromApi(){
        val listOfCountries = mutableListOf<Country>()
        val disposable = countriesUseCase.execute(
            Consumer { allCountries ->
                listOfCountries.addAll(allCountries)
                val task = addCountriesToDB(listOfCountries)
                val thread = Thread(task)
                thread.start()
            },
            Consumer {
                Log.d(it, "error :(")
            }
        )
        compositeDisposable.add(disposable)
    }

    private fun addCountriesToDB(countries: List<Country>) = Runnable {
        database.countryDAO().insertAll(countries)
    }

    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            getCountriesFromApi()
        }
    }
}
