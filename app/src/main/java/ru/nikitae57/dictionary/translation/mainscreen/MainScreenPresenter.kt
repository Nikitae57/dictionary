package ru.nikitae57.dictionary.translation.mainscreen

import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.domain.translation.savedtranslation.GetSavedTranslationsUseCase
import ru.nikitae57.domain.translation.translate.TranslateUseCase
import javax.inject.Inject

@InjectViewState
class MainScreenPresenter @Inject constructor(
    private val router: Router,
    initialStateMapper: MainScreenInitialStateModelMapper,
    private val successStateMapper: MainScreenSuccessStateMapper,
    private val errorStateModelMapper: MainScreenErrorStateModelMapper,
    private val translateUseCase: TranslateUseCase,
    private val savedTranslationsUseCase: GetSavedTranslationsUseCase,
) : BasePresenter<MainScreenView>() {
    init {
        viewState.showInitialState(initialStateMapper())
    }

    fun loadSavedTranslations() {
        viewState.showLoadingState()

        try {
            savedTranslationsUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .doOnError { showError() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    viewState.showSuccessState(successStateMapper(it))
                }
                .also { addToDisposables(it) }
        } catch (ex: Exception) {
            showError()
        }
    }

    private fun showError() = viewState.showErrorState(
        errorStateModelMapper(tryAgainAction = ::loadSavedTranslations)
    )
}