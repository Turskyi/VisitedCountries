package ua.turskyi.data.common.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ua.turskyi.data.db.CountriesDataBase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application) = Room.databaseBuilder(
        context,
        CountriesDataBase::class.java,
        "countries.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideCountryDao(dataBase: CountriesDataBase) = dataBase.countryDAO()
}