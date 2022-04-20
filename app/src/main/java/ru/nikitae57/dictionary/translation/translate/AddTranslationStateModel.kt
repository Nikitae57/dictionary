package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.translation.models.LanguageStateModel

sealed class AddTranslationStateModel {
    data class Initial(
        val addButtonText: CharSequence,
        val wordInputHint: CharSequence,
        val languageLabels: List<LanguageStateModel>
    ) : AddTranslationStateModel()

    object Loading : AddTranslationStateModel()

    data class TranslationAdded(
        val messageText: CharSequence,
        val closeButtonText: CharSequence,
    )
}