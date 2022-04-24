package ru.nikitae57.dictionary.translation.savedtranslations

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface SavedTranslationsView : MvpView {
    @SingleState
    fun showInitialState(state: SavedTranslationsStateModel.Initial)

    @SingleState
    fun showLoadingState()

    @SingleState
    fun showSuccessState(state: SavedTranslationsStateModel.Success)

    @SingleState
    fun showErrorState(state: SavedTranslationsStateModel.Error)
}