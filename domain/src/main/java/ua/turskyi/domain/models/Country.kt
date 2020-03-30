package ua.turskyi.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ua.turskyi.domain.models.Country.Companion.COLUMN_NAME
import ua.turskyi.domain.models.Country.Companion.TABLE_NAME

//data class Country (
//    var id: Int,
//    val name: String,
//    val flag: String,
//    var visited: Boolean?
//)

@Entity(tableName = TABLE_NAME , indices = [Index(value = [COLUMN_NAME], unique = true)])
data class Country (
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
}