package ua.turskyi.visitedcountries.common.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ua.turskyi.visitedcountries.common.App
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