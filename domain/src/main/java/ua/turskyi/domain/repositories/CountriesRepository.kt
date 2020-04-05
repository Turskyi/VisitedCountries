package ua.turskyi.domain.repositories

import io.reactivex.Single
import ua.turskyi.domain.model.Country

interface CountriesRepository {
    fun getCountriesFromDb(): Single<List<Country>>
    fun getCountriesFromApi(): Single<List<Country>>
    fun addCountriesToDb(countries: List<Country>)
    fun markAsVisited(country: Country)
    fun getVisitedRxLiveAll(): Single<List<Country>>
    fun getCountNotVisitedRx(): Single<Int>
    fun removeFromVisited(country: Country)
}