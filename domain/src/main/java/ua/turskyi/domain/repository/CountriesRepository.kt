package ua.turskyi.domain.repository

import io.reactivex.Single
import ua.turskyi.domain.model.Country

interface CountriesRepository {
    fun getCountriesFromApi(): Single<List<Country>>
    fun getCountriesFromDb(): Single<List<Country>>
    fun addModelCountriesToDb(countries: List<Country>): Runnable
    fun markAsVisited(country: Country)
    fun getVisitedCountriesFromDb(): Single<List<Country>>
    fun getNumNotVisitedCountries(): Single<Int>
    fun removeFromVisited(country: Country)
    fun getCountriesByRange(limit: Int, offset: Int): Single<List<Country>>
}