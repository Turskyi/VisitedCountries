package ua.turskyi.data.repository

import io.reactivex.Single
import ua.turskyi.data.api.service.CountriesApiService
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

class CountriesApiRepositoryImpl @Inject constructor(
    private val countriesApiService: CountriesApiService
) : CountriesRepository {

    override fun getCountries(): Single<List<Country>> {
        return countriesApiService.getCountriesFromApi().map {
            it.map { countryResponse ->
                countryResponse.mapToDomain()
            }
        }
    }
}