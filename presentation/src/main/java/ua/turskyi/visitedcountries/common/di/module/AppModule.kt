package ua.turskyi.visitedcountries.common.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.App
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(app: App): Application = app

    @Singleton
    @Provides
    fun provideApplicationContext(app: App): Context = app.applicationContext
}