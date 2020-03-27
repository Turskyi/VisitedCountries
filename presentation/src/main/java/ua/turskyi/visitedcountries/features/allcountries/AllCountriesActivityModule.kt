package ua.turskyi.visitedcountries.features.allcountries

import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.common.di.InjectionViewModelProvider
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection

@Module
class AllCountriesActivityModule {

    @ViewModelInjection
    @Provides
    fun provideViewModel(
        activity: AllCountriesActivity,
        provider: InjectionViewModelProvider<AllCountriesActivityViewModel>
    ): AllCountriesActivityViewModel  = provider.get(activity)
}