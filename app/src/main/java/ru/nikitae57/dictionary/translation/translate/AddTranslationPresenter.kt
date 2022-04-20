package ru.nikitae57.dictionary.translation.translate

import android.util.Log
import io.reactivex.subjects.PublishSubject
import moxy.InjectViewState
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.translation.savetranslation.SaveTranslationUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class AddTranslationPresenter @Inject constructor(
    private val successStateMapper: AddTranslationSuccessStateMapper,
    private val saveTranslationUseCase: SaveTranslationUseCase,
    private val wordDomainModelMapper: WordDomainModelMapper,
    private val errorStateMapper: AddTranslationErrorStateMapper
) : BasePresenter<AddTranslationView>() {

    private var translation: WordStateModel? = null
    private var savedTranslations: MutableList<WordStateModel> = mutableListOf()
    private val textToTranslateSubject = PublishSubject.create<WordStateModel>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showSuccessState(successStateMapper(savedTranslations))
        subscribeToInputChanges()
    }

    fun onChangeTextToTranslate(text: String, languageLabel: String) =
        textToTranslateSubject.onNext(WordStateModel(text = text, languageLabel = languageLabel))

    fun onSaveTranslation() {
        translation?.let { wordStateModel ->
            viewState.showLoadingState()

            saveTranslationUseCase.invoke(wordDomainModel = wordDomainModelMapper(wordStateModel))
                .subscribe({
                    savedTranslations.add(wordStateModel)
                    viewState.showSuccessState(successStateMapper(savedTranslations))
                }, {
                    viewState.showErrorState(errorStateMapper())
                })
                .also { addToDisposables(it) }
        }
    }

    private fun subscribeToInputChanges() = textToTranslateSubject
        .debounce(500L, TimeUnit.MILLISECONDS)
        .map {
            Log.d(TAG, "lang=${it.languageLabel}, text:${it.text}")
            it
        }
        .subscribe {
            // TODO scratch code
            viewState.showTranslation(it)
        }.also { addToDisposables(it) }

    companion object {
        val TAG: String = AddTranslationPresenter::class.java.name
    }
}