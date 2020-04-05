package ua.turskyi.data.api.modelresponse

import com.google.gson.annotations.SerializedName
import ua.turskyi.data.db.modelentity.CountryLocal
import ua.turskyi.domain.model.Country

typealias CountryListResponse = MutableList<CountryResponse>
class CountryResponse(
    var id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String,
    var visited: Boolean?
) {
    fun mapToLocal() = CountryLocal(
        id = id,
        name = name,
        flag = flag,
        visited = visited
    )

    fun mapToDomain() = Country(
        id = id,
        name = name,
        flag = flag,
        visited = visited
    )
}