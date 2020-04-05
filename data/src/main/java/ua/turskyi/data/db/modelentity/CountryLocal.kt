package ua.turskyi.data.db.modelentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.turskyi.data.db.modelentity.CountryLocal.Companion.COLUMN_NAME
import ua.turskyi.data.db.modelentity.CountryLocal.Companion.TABLE_NAME
import ua.turskyi.domain.model.Country

@Entity(tableName = TABLE_NAME , indices = [Index(value = [COLUMN_NAME], unique = true)])
data class CountryLocal (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COLUMN_ID) var id: Int,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_FLAG)  val flag: String,
    @ColumnInfo(name = COLUMN_VISITED) var visited: Boolean?
) {
    companion object {
        const val TABLE_NAME = "Countries"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_FLAG = "flag"
        const val COLUMN_VISITED = "visited"
    }
    fun mapToDomain() = Country(
        id = id,
        name = name,
        flag = flag,
        visited = visited
    )
}