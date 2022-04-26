package ru.nikitae57.dictionary.translation.translate

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AddTranslationView : MvpView {
    @AddToEndSingle
    fun showLoadingState()

    @AddToEndSingle
    fun showTranslationLoadingState()

    @AddToEndSingle
    fun showTranslation(translation: CharSequence, shouldBlockTranslateButton: Boolean)

    @AddToEndSingle
    fun updateLanguages(fromLanguageLabels: List<CharSequence>, toLanguageLabels: List<CharSequence>)

    @AddToEndSingle
    fun showSuccessState(state: AddTranslationStateModel.Success)

    @AddToEndSingle
    fun showErrorState(state: AddTranslationStateModel.Error)
}