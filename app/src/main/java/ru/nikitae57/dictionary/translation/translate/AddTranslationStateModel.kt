package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.translation.models.WordStateModel

sealed class AddTranslationStateModel {
    data class Success(
        val addButtonText: CharSequence,
        val wordInputHint: CharSequence,
        val languageLabels: List<CharSequence>,
        val savedTranslations: List<WordStateModel>
    ) : AddTranslationStateModel()

    data class Error(val errorMessage: CharSequence)
}