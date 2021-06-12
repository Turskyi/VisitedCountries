package ua.turskyi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.turskyi.data.db.dao.CountriesDao
import ua.turskyi.data.db.modelentity.CountryLocal

@Database(
    entities = [CountryLocal::class],
    version = 1,
    exportSchema = false
)

abstract class CountriesDataBase : RoomDatabase() {
    abstract fun countryDAO(): CountriesDao
}