package ua.turskyi.data.api.services

import io.reactivex.Single
import retrofit2.http.GET
import ua.turskyi.data.api.modelresponse.CountryListResponse

interface CountriesApiService {
    @GET("rest/v2/all")
    fun getCountriesFromApi(): Single<CountryListResponse>
}