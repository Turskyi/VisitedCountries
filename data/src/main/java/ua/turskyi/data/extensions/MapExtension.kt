package ua.turskyi.data.extensions

import ua.turskyi.data.db.modelentity.CountryLocal
import ua.turskyi.domain.model.Country

fun List<Country>.mapModelListToLocalList() = mapTo(mutableListOf(), {
    it.mapModelToLocal()
})

fun Country.mapModelToLocal() = CountryLocal(id, name, flag, visited)

