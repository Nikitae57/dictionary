package ru.nikitae57.dictionary.translation.translate

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface AddTranslationView : MvpView {
    @SingleState
    fun showLoadingState()

    @SingleState
    fun showTranslationBlockedState()

    @SingleState
    fun showTranslationLoadingState()

    @SingleState
    fun showTranslation(translation: CharSequence)

    @SingleState
    fun updateLanguages(fromLanguageLabels: List<CharSequence>, toLanguageLabels: List<CharSequence>)

    @SingleState
    fun showSuccessState(state: AddTranslationStateModel.Success)

    @SingleState
    fun showErrorState(state: AddTranslationStateModel.Error)
}