package ua.turskyi.data.api.modelresponse

import com.google.gson.annotations.SerializedName
import ua.turskyi.domain.models.Country

typealias CountryListResponse = MutableList<CountryResponse>
class CountryResponse(
    var id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("flag") val flag: String,
    var visited: Boolean?
) {
    fun mapToDomain() = Country(
        id = id,
        name = name,
        flag = flag,
        visited = visited
    )
}