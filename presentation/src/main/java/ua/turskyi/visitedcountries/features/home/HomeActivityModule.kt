package ua.turskyi.visitedcountries.features.home

import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.common.di.InjectionViewModelProvider
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection

@Module
class HomeActivityModule {

    @ViewModelInjection
    @Provides
    fun provideViewModel(
        activity: HomeActivity,
        provider: InjectionViewModelProvider<HomeActivityViewModel>
    ): HomeActivityViewModel  = provider.get(activity)
}