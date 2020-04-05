package ua.turskyi.data.extensions

import ua.turskyi.data.api.modelresponse.CountryResponse
import ua.turskyi.data.db.modelentity.CountryLocal
import ua.turskyi.domain.model.Country

fun List<CountryResponse>.mapCountryResponse() = this.mapTo(
    mutableListOf(), {
        it.mapToCountry()
    }
)

fun CountryLocal.mapToCountry() = Country(id, name, flag, visited)

fun CountryResponse.mapToCountry() = Country(id, name, flag, visited)

fun List<CountryLocal>.mapCountryLocalListToCountryList() = mapTo(
    mutableListOf(), {
        it.mapToCountry()
    }
)

fun List<Country>.mapToCountryLocalList() = mapTo(mutableListOf(), {
    it.mapToCountryLocal()
})

fun Country.mapToCountryLocal() = CountryLocal(id, name, flag, visited)

