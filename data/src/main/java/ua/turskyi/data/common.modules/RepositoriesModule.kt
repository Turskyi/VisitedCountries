package ua.turskyi.data.common.modules

import dagger.Module
import dagger.Provides
import ua.turskyi.data.repositories.CountriesApiRepositoryImpl
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideCountriesApiRepository(
        countriesApiRepositoryImpl: CountriesApiRepositoryImpl
    ): CountriesRepository = countriesApiRepositoryImpl
}