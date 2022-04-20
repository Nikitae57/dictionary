package ru.nikitae57.dictionary.translation.savedtranslations

import com.github.terrakok.cicerone.Router
import io.reactivex.rxkotlin.subscribeBy
import moxy.InjectViewState
import ru.nikitae57.dictionary.Screens
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.translation.savedtranslations.GetSavedTranslationsUseCase
import javax.inject.Inject

@InjectViewState
class SavedTranslationsPresenter @Inject constructor(
    private val router: Router,
    private val initialStateMapper: SavedTranslationsInitialStateModelMapper,
    private val successStateMapper: SavedTranslationsSuccessStateMapper,
    private val errorStateModelMapper: SavedTranslationsErrorStateModelMapper,
    private val getSavedTranslationsUseCase: GetSavedTranslationsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<SavedTranslationsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showInitialState(initialStateMapper())
        loadSavedTranslations()
    }

    fun onAddTranslationClicked() {
        router.navigateTo(Screens.addTranslation())
    }

    private fun loadSavedTranslations() {
        viewState.showLoadingState()

        try {
            getSavedTranslationsUseCase.invoke()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                    onError = { showError() },
                    onSuccess = { viewState.showSuccessState(successStateMapper(it)) }
                )
                .also { addToDisposables(it) }
        } catch (ex: Exception) {
            showError()
        }
    }

    private fun showError() = viewState.showErrorState(
        errorStateModelMapper(tryAgainAction = ::loadSavedTranslations)
    )
}