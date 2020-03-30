package ua.turskyi.data.repositories

import io.reactivex.Single
import ua.turskyi.data.db.dao.CountryDAO
import ua.turskyi.domain.models.Country
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Inject

//class CountriesDbRepositoryImpl @Inject constructor(
//    private val countriesFromDb: CountryDAO
//) : CountriesRepository {

//    override fun getCountries(): Single<List<Country>> {
//        return countriesFromDb.getRxLiveAll().map {
//            it.map { countryResponse ->
//                countryResponse.mapToDomain()
//            }
//        }
//    }
//}