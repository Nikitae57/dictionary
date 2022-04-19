package ru.nikitae57.dictionary.translation.mainscreen

import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.translation.savedtranslation.GetSavedTranslationsUseCase
import javax.inject.Inject

@InjectViewState
class MainScreenPresenter @Inject constructor(
    private val router: Router,
    initialStateMapper: MainScreenInitialStateModelMapper,
    private val successStateMapper: MainScreenSuccessStateMapper,
    private val errorStateModelMapper: MainScreenErrorStateModelMapper,
    private val getSavedTranslationsUseCase: GetSavedTranslationsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BasePresenter<MainScreenView>() {
    init {
        viewState.showInitialState(initialStateMapper())
    }

    fun loadSavedTranslations() {
        viewState.showLoadingState()

        try {
            getSavedTranslationsUseCase.invoke()
                .subscribeOn(schedulerProvider.io())
                .doOnError { showError() }
                .observeOn(schedulerProvider.ui())
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