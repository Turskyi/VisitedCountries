package ua.turskyi.data.repository

import io.reactivex.Single
import ua.turskyi.data.network.service.CountriesApiService
import ua.turskyi.data.db.dao.CountriesDao
import ua.turskyi.data.extensions.mapModelToLocal
import ua.turskyi.data.extensions.mapModelListToLocalList
import ua.turskyi.domain.model.Country
import ua.turskyi.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesApiService: CountriesApiService,
    private val countriesDao: CountriesDao
) : CountriesRepository {

    override fun getCountriesFromApi(): Single<List<Country>> {
        return countriesApiService.getCountriesFromApi().map {
            it.map { countryResponse ->
                countryResponse.mapToDomain()
            }
        }
    }

    override fun markAsVisited(country: Country) {
        val countryLocal = country.mapModelToLocal()
        countryLocal.visited = true
        return countriesDao.insert(countryLocal)
    }

    override fun removeFromVisited(country: Country) {
        val countryLocal = country.mapModelToLocal()
        countryLocal.visited = false
        return countriesDao.insert(countryLocal)
    }

    override fun getCountriesByRange(limit: Int, offset: Int): Single<List<Country>> {
        return countriesDao.getCountriesByRange(limit,offset).map { localList ->
            localList.map {countryLocal ->
                countryLocal.mapToDomain()
            }
        }
    }

    override fun addModelCountriesToDb(countries: List<Country>) = Runnable {
        countriesDao.insertAllCountries(countries.mapModelListToLocalList())
    }

    override fun getVisitedCountriesFromDb(): Single<List<Country>> {
        return countriesDao.getVisitedCountries().map {
            it.map { countryLocal ->
                countryLocal.mapToDomain()
            }
        }
    }

    override fun getNumNotVisitedCountries(): Single<Int> {
        return countriesDao.getNumNotVisitedCountries()
    }

    override fun getCountriesFromDb(): Single<List<Country>> {
        return countriesDao.getAllCountriesFromDb().map {
            it.map { countryLocal ->
                countryLocal.mapToDomain()
            }
        }
    }
}