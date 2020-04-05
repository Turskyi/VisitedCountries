package ua.turskyi.data.repositories

import io.reactivex.Single
import ua.turskyi.data.api.service.CountriesApiService
import ua.turskyi.data.db.dao.CountriesDAO
import ua.turskyi.data.extensions.mapToCountryLocal
import ua.turskyi.data.extensions.mapToCountryLocalList
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesApiService: CountriesApiService,
    private val countriesDAO: CountriesDAO
) : CountriesRepository {

    override fun markAsVisited(country: Country) {
        countriesDAO.insert(country.mapToCountryLocal())
    }

    override fun removeFromVisited(country: Country) {
        return countriesDAO.insert(country.mapToCountryLocal())
    }

    override fun getCountriesFromApi(): Single<List<Country>> {
        return countriesApiService.getCountriesFromApi().map {
            it.map { countryResponse ->
                countryResponse.mapToDomain()
            }
        }
    }

    override fun addCountriesToDb(countries: List<Country>) {
        countriesDAO.insertAll(countries.mapToCountryLocalList())
    }

    override fun getVisitedRxLiveAll(): Single<List<Country>> {
        return countriesDAO.getVisitedRxLiveAll().map {
            it.map { countryLocal ->
                countryLocal.mapToDomain()
            }
        }
    }

    override fun getCountNotVisitedRx(): Single<Int> {
        return countriesDAO.getCountNotVisitedRx()
    }

    override fun getCountriesFromDb(): Single<List<Country>> {
        return countriesDAO.getRxLiveAll().map {
            it.map { countryLocal ->
                countryLocal.mapToDomain()
            }
        }
    }
}