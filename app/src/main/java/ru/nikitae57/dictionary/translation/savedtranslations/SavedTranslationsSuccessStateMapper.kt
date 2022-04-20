package ru.nikitae57.dictionary.translation.savedtranslations

import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel
import ru.nikitae57.dictionary.translation.models.LanguageStateModel
import ru.nikitae57.dictionary.translation.models.LanguagesStateModel
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.LanguagesDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class SavedTranslationsSuccessStateMapper @Inject constructor(
    private val resources: Res
) {
    operator fun invoke(dictionaryEntriesDomainModel: DictionaryEntriesDomainModel) = SavedTranslationsStateModel.Success(
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
            languageLabel = mapLanguage(wordDomainModel.languageLabel),
        )
    }

    private fun mapLanguage(language: LanguagesDomainModel) = when (language) {
        LanguagesDomainModel.RU -> LanguageStateModel(language = LanguagesStateModel.RU, label = resources.getString(R.string.ru_language_label))
        LanguagesDomainModel.EN -> LanguageStateModel(language = LanguagesStateModel.EN, label = resources.getString(R.string.en_language_label))
        LanguagesDomainModel.FR -> LanguageStateModel(language = LanguagesStateModel.FR, label = resources.getString(R.string.fr_language_label))
    }

    private fun mapLanguageLabel(language: LanguagesDomainModel) = when (language) {
        LanguagesDomainModel.RU -> resources.getString(R.string.ru_language_label)
        LanguagesDomainModel.EN -> resources.getString(R.string.en_language_label)
        LanguagesDomainModel.FR -> resources.getString(R.string.fr_language_label)
    }
}