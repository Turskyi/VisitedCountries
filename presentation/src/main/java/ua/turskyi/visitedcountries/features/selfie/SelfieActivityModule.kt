package ua.turskyi.visitedcountries.features.selfie

import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.common.di.InjectionViewModelProvider
import ua.turskyi.visitedcountries.common.di.qualifiers.ViewModelInjection

@Module
class SelfieActivityModule {
    @ViewModelInjection
    @Provides
    fun provideViewModel(
        activity: SelfieActivity,
        provider: InjectionViewModelProvider<SelfieActivityViewModel>
    ): SelfieActivityViewModel  = provider.get(activity)
}