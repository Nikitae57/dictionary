package ru.nikitae57.data.core.room.translation

import androidx.room.Embedded
import androidx.room.Relation

data class DictionaryEntryWithWordsDataModel(
    @Embedded val dictionaryEntryDataModel: DictionaryEntryDataModel,
    @Relation(
        parentColumn = DictionaryEntryDataModel.ID_COLUMN_NAME,
        entityColumn = WordDataModel.DICTIONARY_ID_COLUMN_NAME,
    )
    val words: List<WordDataModel>
)