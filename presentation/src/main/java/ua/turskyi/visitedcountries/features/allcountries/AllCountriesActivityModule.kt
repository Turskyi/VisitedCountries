package ua.turskyi.visitedcountries.features.allcountries

import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.common.di.InjectionViewModelProvider
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection
import ua.turskyi.visitedcountries.features.allcountries.view.ui.AllCountriesActivity
import ua.turskyi.visitedcountries.features.allcountries.viewmodel.AllCountriesActivityViewModel

@Module
class AllCountriesActivityModule {

    @ViewModelInjection
    @Provides
    fun provideViewModel(
        activity: AllCountriesActivity,
        provider: InjectionViewModelProvider<AllCountriesActivityViewModel>
    ): AllCountriesActivityViewModel = provider.get(activity)
}