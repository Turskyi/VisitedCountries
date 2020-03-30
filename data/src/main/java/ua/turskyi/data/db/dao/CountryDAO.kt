package ua.turskyi.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ua.turskyi.data.db.modelentity.CountryLocal.Companion.TABLE_NAME
import ua.turskyi.domain.models.Country

@Dao
interface CountryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(countries: List<Country>?)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getRxLiveAll(): Single<List<Country>>

    /* using in paging adapters */
    @Query("SELECT * FROM $TABLE_NAME LIMIT :limit OFFSET :offset")
    fun getCountriesByRange(limit: Int, offset: Int): Flowable<List<Country>>

    @Query("SELECT COUNT(${Country.COLUMN_ID}) FROM $TABLE_NAME WHERE ${Country.COLUMN_VISITED} IS null OR ${Country.COLUMN_VISITED} = 0")
    fun getCountNotVisitedRx(): Flowable<Int>

    @Query("SELECT * FROM $TABLE_NAME WHERE ${Country.COLUMN_VISITED} = 1")
    fun getVisitedRxLiveAll(): Flowable<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)
}