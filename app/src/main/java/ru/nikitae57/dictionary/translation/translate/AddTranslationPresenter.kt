package ru.nikitae57.dictionary.translation.translate

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import moxy.InjectViewState
import ru.nikitae57.dictionary.core.AppSchedulerProvider
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.translation.savetranslation.SaveTranslationUseCase
import ru.nikitae57.domain.translation.translate.TranslateUseCase
import ru.nikitae57.domain.translation.translate.TranslationDomainModel
import ru.nikitae57.domain.translation.translate.WordToTranslateDomainModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class AddTranslationPresenter @Inject constructor(
    private val router: Router,
    private val successStateMapper: AddTranslationSuccessStateMapper,
    private val translateUseCase: TranslateUseCase,
    private val saveTranslationUseCase: SaveTranslationUseCase,
    private val dictionaryEntryDomainModelMapper: DictionaryEntryDomainModelMapper,
    private val errorStateMapper: AddTranslationErrorStateMapper,
    private val schedulerProvider: AppSchedulerProvider,
) : BasePresenter<AddTranslationView>() {

    private lateinit var currentTranslation: WordStateModel
    private lateinit var currentInputText: WordStateModel
    private lateinit var fromLanguageLabels: MutableList<CharSequence>
    private lateinit var toLanguageLabels: MutableList<CharSequence>
    private var fromLanguageLabel = ""
    private var toLanguageLabel = ""

    private val textToTranslateSubject = PublishSubject.create<WordToTranslateStateModel>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showSuccessState()
        subscribeToInputChanges()
    }

    fun onBackPressed() = router.exit()

    fun onChangeTextToTranslate(text: String) {
        currentInputText = WordStateModel(text = text, languageLabel = fromLanguageLabel)
        onTextOrLanguageChanged()
    }

    fun onFromLanguageChanged(label: String) {
        fromLanguageLabel = label
        onTextOrLanguageChanged()
    }

    fun onToLanguageChanged(label: String) {
        toLanguageLabel = label
        onTextOrLanguageChanged()
    }

    private fun onTextOrLanguageChanged() {
        textToTranslateSubject.onNext(
            WordToTranslateStateModel(
                text = currentInputText.text.toString(),
                fromLanguageLabel = fromLanguageLabel,
                toLanguageLabel = toLanguageLabel
            )
        )
    }

    fun onSwapLanguages() {
        val toLanguages = toLanguageLabels.apply {
            remove(fromLanguageLabel)
            add(0, fromLanguageLabel)
        }
        val fromLanguages = fromLanguageLabels.apply {
            remove(toLanguageLabel)
            add(0, toLanguageLabel)
        }
        toLanguageLabels = toLanguages
        fromLanguageLabels = fromLanguages

        val toLanguage = toLanguageLabel
        toLanguageLabel = fromLanguageLabel
        fromLanguageLabel = toLanguage

        viewState.updateLanguages(fromLanguageLabels = fromLanguageLabels, toLanguageLabels = toLanguageLabels)
        onTextOrLanguageChanged()
    }

    fun onSaveTranslation() {
        if (currentInputText.text.isEmpty()) return

        viewState.showLoadingState()
        saveTranslationUseCase.invoke(dictionaryEntryDomainModelMapper(from = currentInputText, to = currentTranslation))
            .delay(5L, TimeUnit.SECONDS)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                router.exit()
            }, {
                viewState.showErrorState(errorStateMapper())
            })
            .also { addToDisposables(it) }
    }

    private fun subscribeToInputChanges() = textToTranslateSubject
        .subscribeOn(schedulerProvider.io())
        .debounce(500L, TimeUnit.MILLISECONDS)
        .observeOn(schedulerProvider.ui())
        .map {
            if (it.text.isNotEmpty()) {
                viewState.showTranslationLoadingState()
            }
            it
        }
        .observeOn(schedulerProvider.io())
        .flatMap {
            if (it.text.isEmpty()) {
                Observable.just(
                    TranslationDomainModel(
                        translation = it.text,
                        originalText = currentInputText.text.toString(),
                        fromLanguageLabel = fromLanguageLabel,
                        toLanguageLabel = toLanguageLabel
                    )
                )
            } else {
                val domainModel = WordToTranslateDomainModel(
                    text = it.text,
                    fromLanguageLabel = it.fromLanguageLabel,
                    toLanguageLabel = it.toLanguageLabel
                )
                Log.d(TAG, "Sending to translate: from=$${it.fromLanguageLabel}, to=${it.toLanguageLabel} text:${it.text}")
                translateUseCase(domainModel).toObservable()
            }
        }
        .observeOn(schedulerProvider.ui())
        .subscribe { translationDomainModel ->
            currentTranslation = WordStateModel(text = translationDomainModel.translation, languageLabel = translationDomainModel.toLanguageLabel)
            viewState.showTranslation(translationDomainModel.translation)
        }.also { addToDisposables(it) }

    private fun showSuccessState() {
        val successState = successStateMapper()
        currentInputText = WordStateModel(text = "", languageLabel = successState.fromLanguageLabels.first())
        fromLanguageLabels = successState.fromLanguageLabels.toMutableList()
        toLanguageLabels = successState.toLanguageLabels.toMutableList()
        viewState.showSuccessState(successState)
    }

    companion object {
        val TAG: String = AddTranslationPresenter::class.java.simpleName
    }
}