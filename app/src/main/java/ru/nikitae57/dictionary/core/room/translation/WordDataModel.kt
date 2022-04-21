package ru.nikitae57.dictionary.core.room.translation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WordDataModel.TABLE_NAME)
data class WordDataModel(
    @ColumnInfo(name = ID_COLUMN_NAME)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    // made it "var" for insert operation
    @ColumnInfo(name = DICTIONARY_ID_COLUMN_NAME) var dictionaryId: Long = 0,
    @ColumnInfo(name = TEXT_COLUMN_NAME) val text: String,
    @ColumnInfo(name = LANGUAGE_LABEL_COLUMN_NAME) val languageLabel: String
) {
    companion object {
        const val TABLE_NAME = "words"
        const val ID_COLUMN_NAME = "id"
        const val DICTIONARY_ID_COLUMN_NAME = "dictionary_id"
        const val TEXT_COLUMN_NAME = "text"
        const val LANGUAGE_LABEL_COLUMN_NAME = "language_label"
    }
}