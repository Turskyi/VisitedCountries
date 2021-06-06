package ua.turskyi.data.network.modelresponse

import com.google.gson.annotations.SerializedName
import ua.turskyi.domain.model.Country

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