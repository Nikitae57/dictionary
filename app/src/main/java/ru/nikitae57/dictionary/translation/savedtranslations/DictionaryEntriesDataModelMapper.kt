package ru.nikitae57.dictionary.translation.savedtranslations

import ru.nikitae57.dictionary.core.room.translation.DictionaryEntriesDataModel
import ru.nikitae57.dictionary.core.room.translation.WordDataModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import javax.inject.Inject

class DictionaryEntriesDataModelMapper @Inject constructor() {
    operator fun invoke(dictionaryEntryDomainModel: DictionaryEntryDomainModel): DictionaryEntriesDataModel {
        return DictionaryEntriesDataModel(
            words = dictionaryEntryDomainModel.words.map {
                WordDataModel(text = it.text, languageLabel = it.languageLabel)
            }
        )
    }
}