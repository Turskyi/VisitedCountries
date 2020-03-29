package ua.turskyi.visitedcountries.common.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.turskyi.visitedcountries.features.allcountries.view.ui.AllCountriesActivity
import ua.turskyi.visitedcountries.features.allcountries.AllCountriesActivityModule
import ua.turskyi.visitedcountries.features.home.view.ui.HomeActivity
import ua.turskyi.visitedcountries.features.home.HomeActivityModule
import ua.turskyi.visitedcountries.features.selfie.view.SelfieActivity
import ua.turskyi.visitedcountries.features.selfie.SelfieActivityModule

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivityInjector(): HomeActivity

    @ContributesAndroidInjector(modules = [AllCountriesActivityModule::class])
    abstract fun allCountriesActivityInjector(): AllCountriesActivity

    @ContributesAndroidInjector(modules = [SelfieActivityModule::class])
    abstract fun selfieActivityInjector(): SelfieActivity
}