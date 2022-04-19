package ru.nikitae57.dictionary.translation.mainscreen

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainScreenView : MvpView {
    @AddToEndSingle
    fun showInitialState(state: MainScreenStateModel.Initial)

    @AddToEndSingle
    fun showLoadingState()

    @AddToEndSingle
    fun showSuccessState(state: MainScreenStateModel.Success)

    @AddToEndSingle
    fun showErrorState(state: MainScreenStateModel.Error)
}