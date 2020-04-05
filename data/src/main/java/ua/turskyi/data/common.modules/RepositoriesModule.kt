package ua.turskyi.data.common.modules

import dagger.Module
import dagger.Provides
import ua.turskyi.data.repository.CountriesRepositoryImpl
import ua.turskyi.domain.repository.CountriesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideCountriesApiRepository(
        countriesRepositoryImpl: CountriesRepositoryImpl
    ): CountriesRepository = countriesRepositoryImpl
}