package ua.turskyi.data.common.modules

import dagger.Module
import dagger.Provides
import ua.turskyi.data.repository.CountriesApiRepositoryImpl
import ua.turskyi.domain.repositories.CountriesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        usersApiRepositoryImpl: CountriesApiRepositoryImpl
    ): CountriesRepository = usersApiRepositoryImpl
}