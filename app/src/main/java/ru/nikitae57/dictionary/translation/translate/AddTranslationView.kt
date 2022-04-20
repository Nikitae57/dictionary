package ru.nikitae57.dictionary.translation.translate

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.nikitae57.dictionary.translation.models.WordStateModel

interface AddTranslationView : MvpView {
    @SingleState
    fun showLoadingState()

    @SingleState
    fun showTranslationLoadingState()

    @SingleState
    fun showTranslation(translation: WordStateModel)

    @SingleState
    fun showSuccessState(state: AddTranslationStateModel.Success)

    @SingleState
    fun showErrorState(state: AddTranslationStateModel.Error)
}