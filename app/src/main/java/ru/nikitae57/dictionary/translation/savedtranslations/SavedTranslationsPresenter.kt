package ru.nikitae57.dictionary.translation.savedtranslations

import com.github.terrakok.cicerone.Router
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import moxy.InjectViewState
import ru.nikitae57.dictionary.Screens
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.translation.savedtranslations.GetSavedTranslationsUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class SavedTranslationsPresenter @Inject constructor(
    private val router: Router,
    private val initialStateMapper: SavedTranslationsInitialStateModelMapper,
    private val successStateMapper: SavedTranslationsSuccessStateMapper,
    private val errorStateModelMapper: SavedTranslationsErrorStateModelMapper,
    private val getSavedTranslationsUseCase: GetSavedTranslationsUseCase,
    private val schedulerProvider: SchedulerProvider,
) : BasePresenter<SavedTranslationsView>() {

    private val textToSearchSubject = PublishSubject.create<String>()
    private lateinit var dictionaryEntryStateModels: DictionaryEntriesStateModel

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        subscribeToTextChanges()
        viewState.showInitialState(initialStateMapper())
    }

    fun onAddTranslationClicked() {
        router.navigateTo(Screens.addTranslation())
    }

    fun onSearchTextChanged(text: String) = textToSearchSubject.onNext(text)

    private fun subscribeToTextChanges() {
        textToSearchSubject
            .debounce(500L, TimeUnit.MILLISECONDS)
            .observeOn(schedulerProvider.ui())
            .subscribe { inputText ->
                val filteredEntries = if (inputText.isNotEmpty()) {
                    dictionaryEntryStateModels.copy(
                        entries = dictionaryEntryStateModels.entries.filter {
                            it.words.any { wordStateModel ->
                                wordStateModel.text.contains(inputText, ignoreCase = true)
                            }
                        }
                    )
                } else {
                    dictionaryEntryStateModels
                }
                viewState.showSuccessState(SavedTranslationsStateModel.Success(filteredEntries))
            }
            .also { addToDisposables(it) }
    }

    fun loadSavedTranslations() {
        viewState.showLoadingState()

        try {
            getSavedTranslationsUseCase.invoke()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                    onError = { showError() },
                    onSuccess = {
                        val state = successStateMapper(it)
                        dictionaryEntryStateModels = state.dictionaryEntriesStateModel
                        viewState.showSuccessState(state)
                    }
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