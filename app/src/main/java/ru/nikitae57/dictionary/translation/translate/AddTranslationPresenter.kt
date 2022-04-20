package ru.nikitae57.dictionary.translation.translate

import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AddTranslationPresenter @Inject constructor(
    initialStateMapper: AddTranslationInitialStateMapper
) : MvpPresenter<AddTranslationView>() {
    init {
        viewState.showInitialState(initialStateMapper())
    }
}