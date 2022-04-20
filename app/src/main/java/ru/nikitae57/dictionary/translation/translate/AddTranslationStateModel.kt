package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.translation.models.LanguageStateModel
import ru.nikitae57.dictionary.translation.models.WordStateModel

sealed class AddTranslationStateModel {
    object Loading : AddTranslationStateModel()

    data class Success(
        val addButtonText: CharSequence,
        val wordInputHint: CharSequence,
        val languageLabels: List<LanguageStateModel>,
        val savedTranslations: List<WordStateModel>
    ) : AddTranslationStateModel()

    data class Error(val errorMessage: CharSequence)
}