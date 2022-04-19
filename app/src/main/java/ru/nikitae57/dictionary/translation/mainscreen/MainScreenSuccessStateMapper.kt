package ru.nikitae57.dictionary.translation.mainscreen

import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel
import ru.nikitae57.dictionary.translation.models.LanguagesStateModel
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.LanguagesDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class MainScreenSuccessStateMapper @Inject constructor(
    private val resources: Res
) {
    operator fun invoke(dictionaryEntriesDomainModel: DictionaryEntriesDomainModel) = MainScreenStateModel.Success(
        dictionaryEntryStateModels = DictionaryEntriesStateModel(
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
            language = mapLanguage(wordDomainModel.language),
            languageLabel = mapLanguageLabel(wordDomainModel.language)
        )
    }

    private fun mapLanguage(language: LanguagesDomainModel) = when (language) {
        LanguagesDomainModel.RU -> LanguagesStateModel.RU
        LanguagesDomainModel.EN -> LanguagesStateModel.EN
        LanguagesDomainModel.FR -> LanguagesStateModel.FR
    }

    private fun mapLanguageLabel(language: LanguagesDomainModel) = when (language) {
        LanguagesDomainModel.RU -> resources.getString(R.string.ru_language_label)
        LanguagesDomainModel.EN -> resources.getString(R.string.en_language_label)
        LanguagesDomainModel.FR -> resources.getString(R.string.fr_language_labell)
    }
}