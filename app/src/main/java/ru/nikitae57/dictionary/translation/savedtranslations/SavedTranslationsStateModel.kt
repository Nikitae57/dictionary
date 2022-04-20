package ru.nikitae57.dictionary.translation.savedtranslations

sealed class SavedTranslationsStateModel {
    data class Initial(
        val textInputHintText: CharSequence,
    ) : SavedTranslationsStateModel()

    data class Error(
        val tryAgainButtonText: CharSequence,
        val errorMessage: String,
        val tryAgainAction: () -> Unit
    ) : SavedTranslationsStateModel()
}
