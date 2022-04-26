package ru.nikitae57.dictionary.translation.translate

import androidx.annotation.DrawableRes

sealed class AddTranslationStateModel {
    data class Success(
        val addButtonText: CharSequence,
        val wordInputHint: CharSequence,
        val fromLanguageLabels: List<CharSequence>,
        val fromLanguagesSpinnerLabel: CharSequence,
        val toLanguageLabels: List<CharSequence>,
        val toLanguagesSpinnerLabel: CharSequence,
        @DrawableRes val swapLanguagesIconId: Int
    ) : AddTranslationStateModel()

    data class Error(val errorMessage: CharSequence) : AddTranslationStateModel()
}