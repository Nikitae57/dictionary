package ru.nikitae57.dictionary.translation.savedtranslations

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel

interface SavedTranslationsView : MvpView {
    @SingleState
    fun showInitialState(state: SavedTranslationsStateModel.Initial)

    @SingleState
    fun showLoadingState()

    @SingleState
    fun showSuccessState(dictionaryEntriesStateModel: DictionaryEntriesStateModel)

    @SingleState
    fun showErrorState(state: SavedTranslationsStateModel.Error)
}