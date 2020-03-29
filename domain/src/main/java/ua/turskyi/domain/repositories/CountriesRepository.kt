package ua.turskyi.domain.repositories

import io.reactivex.Single
import ua.turskyi.domain.models.Country

interface CountriesRepository {
    fun getCountries(): Single<List<Country>>
}