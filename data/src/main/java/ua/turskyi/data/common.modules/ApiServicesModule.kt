package ua.turskyi.data.common.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ua.turskyi.data.network.service.CountriesApiService
import javax.inject.Singleton

@Module
class ApiServicesModule {
    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): CountriesApiService =
        retrofit.create(CountriesApiService::class.java)
}