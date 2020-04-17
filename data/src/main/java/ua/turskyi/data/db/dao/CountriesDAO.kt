package ua.turskyi.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ua.turskyi.data.db.modelentity.CountryLocal
import ua.turskyi.data.db.modelentity.CountryLocal.Companion.TABLE_NAME

@Dao
interface CountriesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCountries(countries: List<CountryLocal>?)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllCountriesFromDb(): Single<List<CountryLocal>>

    /* using in paging adapters */
    @Query("SELECT * FROM $TABLE_NAME LIMIT :limit OFFSET :offset")
    fun getCountriesByRange(limit: Int, offset: Int): Single<List<CountryLocal>>

    @Query("SELECT COUNT(${CountryLocal.COLUMN_ID}) FROM $TABLE_NAME WHERE ${CountryLocal.COLUMN_VISITED} IS null OR ${CountryLocal.COLUMN_VISITED} = 0")
    fun getNumNotVisitedCountries(): Single<Int>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${CountryLocal.COLUMN_VISITED} = 1")
    fun getVisitedCountries(): Single<List<CountryLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: CountryLocal)
}