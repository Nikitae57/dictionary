package ru.nikitae57.dictionary.translation.savedtranslations

import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel

sealed class SavedTranslationsStateModel {
    data class Initial(
        val textInputHintText: CharSequence,
    ) : SavedTranslationsStateModel()

    data class Success(
        val dictionaryEntriesStateModel: DictionaryEntriesStateModel
    )

    data class Error(
        val tryAgainButtonText: CharSequence,
        val errorMessage: String,
        val tryAgainAction: () -> Unit
    ) : SavedTranslationsStateModel()
}
