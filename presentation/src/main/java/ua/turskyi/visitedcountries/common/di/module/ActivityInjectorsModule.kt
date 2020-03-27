package ua.turskyi.visitedcountries.common.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.turskyi.visitedcountries.features.allcountries.AllCountriesActivity
import ua.turskyi.visitedcountries.features.allcountries.AllCountriesActivityModule
import ua.turskyi.visitedcountries.features.home.HomeActivity
import ua.turskyi.visitedcountries.features.home.HomeActivityModule

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivityInjector(): HomeActivity

    @ContributesAndroidInjector(modules = [AllCountriesActivityModule::class])
    abstract fun allCountriesActivityInjector(): AllCountriesActivity
}