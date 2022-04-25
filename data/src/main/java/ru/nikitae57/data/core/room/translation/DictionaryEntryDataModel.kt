package ru.nikitae57.data.core.room.translation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DictionaryEntryDataModel.TABLE_NAME)
data class DictionaryEntryDataModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
) {
    companion object {
        const val TABLE_NAME = "dictionary_entries"
        const val ID_COLUMN_NAME = "id"
    }
}