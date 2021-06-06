package ua.turskyi.data.network.service

import io.reactivex.Single
import retrofit2.http.GET
import ua.turskyi.data.network.modelresponse.CountryListResponse

interface CountriesApiService {
    @GET("rest/v2/all")
    fun getCountriesFromApi(): Single<CountryListResponse>
}