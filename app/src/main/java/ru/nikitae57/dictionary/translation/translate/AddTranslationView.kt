package ru.nikitae57.dictionary.translation.translate

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

interface AddTranslationView : MvpView {
    @AddToEnd
    fun showLoadingState()

    @AddToEnd
    fun showTranslationLoadingState()

    @AddToEnd
    fun showTranslation(translation: CharSequence, shouldBlockTranslateButton: Boolean)

    @AddToEnd
    fun updateLanguages(fromLanguageLabels: List<CharSequence>, toLanguageLabels: List<CharSequence>)

    @AddToEnd
    fun showSuccessState(state: AddTranslationStateModel.Success)

    @AddToEnd
    fun showErrorState(state: AddTranslationStateModel.Error)
}