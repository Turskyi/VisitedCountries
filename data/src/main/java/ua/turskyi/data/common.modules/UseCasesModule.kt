package ua.turskyi.data.common.modules

import dagger.Module
import dagger.Provides
import ua.turskyi.data.usecases.GetCountriesUseCaseImpl
import ua.turskyi.domain.usecases.GetCountriesUseCase
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideCountriesUseCases(
        countriesUseCasesImpl: GetCountriesUseCaseImpl
    ): GetCountriesUseCase = countriesUseCasesImpl
}