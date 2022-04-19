package ru.nikitae57.dictionary.translation.mainscreen

import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel

sealed class MainScreenStateModel {
    data class Initial(
        val textInputHintText: CharSequence,
    ) : MainScreenStateModel()

    data class Success(
        val dictionaryEntryStateModels: DictionaryEntriesStateModel
    ) : MainScreenStateModel()

    data class Error(
        val tryAgainButtonText: CharSequence,
        val errorMessage: String,
        val tryAgainAction: () -> Unit
    ) : MainScreenStateModel()
}
