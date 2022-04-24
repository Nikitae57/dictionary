package ru.nikitae57.dictionary.translation.savedtranslations

import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class SavedTranslationsSuccessStateMapper @Inject constructor() {
    operator fun invoke(dictionaryEntriesDomainModel: DictionaryEntriesDomainModel) =
        SavedTranslationsStateModel.Success(
            dictionaryEntriesStateModel = DictionaryEntriesStateModel(
                entries = mapEntries(dictionaryEntriesDomainModel.entries)
            )
        )

    private fun mapEntries(entries: List<DictionaryEntryDomainModel>) = entries.map { dictionaryEntryDomainModel ->
        DictionaryEntryStateModel(
            words = mapWords(dictionaryEntryDomainModel.words)
        )
    }

    private fun mapWords(words: List<WordDomainModel>) = words.map { wordDomainModel ->
        WordStateModel(
            text = wordDomainModel.text,
            languageLabel = wordDomainModel.languageLabel,
        )
    }
}