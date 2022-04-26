package ru.nikitae57.data.core.room.translation.savedtranslations

import ru.nikitae57.data.core.room.translation.DictionaryEntryWithWordsDataModel
import ru.nikitae57.data.core.room.translation.WordDataModel
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class DictionaryEntryDomainModelMapper @Inject constructor() {
    operator fun invoke(dataModels: List<DictionaryEntryWithWordsDataModel>) =
        DictionaryEntriesDomainModel(
            entries = dataModels.map { dataModel ->
                DictionaryEntryDomainModel(words = mapWords(dataModel.words))
            }
        )

    private fun mapWords(words: List<WordDataModel>) = words.map { wordDataModel ->
        WordDomainModel(
            text = wordDataModel.text,
            languageLabel = wordDataModel.languageLabel
        )
    }
}