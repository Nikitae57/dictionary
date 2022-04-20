package ru.nikitae57.dictionary.translation.translate

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface AddTranslationView : MvpView {
    @SingleState
    fun showInitialState(state: AddTranslationStateModel.Initial)

    @SingleState
    fun showLoadingState()

    @SingleState
    fun showTranslationAddedState()
}