package ru.nikitae57.dictionary.translation.translate

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.subjects.PublishSubject
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.translation.savetranslation.SaveTranslationUseCase
import ru.nikitae57.domain.translation.translate.TextToTranslateDomainModel
import ru.nikitae57.domain.translation.translate.TranslateUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddTranslationPresenter @Inject constructor(
    private val router: Router,
    private val successStateMapper: AddTranslationSuccessStateMapper,
    private val translateUseCase: TranslateUseCase,
    private val saveTranslationUseCase: SaveTranslationUseCase,
    private val dictionaryEntryDomainModelMapper: DictionaryEntryDomainModelMapper,
    private val errorStateMapper: AddTranslationErrorStateMapper,
    private val schedulerProvider: SchedulerProvider,
) : BasePresenter<AddTranslationView>() {

    private var currentTranslation = WordStateModel(text = "", languageLabel = "")
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
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                router.exit()
            }, {
                viewState.showErrorState(errorStateMapper(it.message))
            })
            .also { addToDisposables(it) }
    }

    private fun subscribeToInputChanges() = textToTranslateSubject
        .subscribeOn(schedulerProvider.io())
        .debounce(500L, TimeUnit.MILLISECONDS)
        .observeOn(schedulerProvider.ui())
        .doOnNext {
            if (it.text.isNotEmpty() && it.text != currentTranslation.text) {
                viewState.showTranslationLoadingState()
            }
        }
        .observeOn(schedulerProvider.io())
        .flatMap {
            val domainModel = TextToTranslateDomainModel(
                text = it.text,
                fromLanguageLabel = it.fromLanguageLabel,
                toLanguageLabel = it.toLanguageLabel
            )
            Log.d(TAG, "Sending to translate: from=$${it.fromLanguageLabel}, to=${it.toLanguageLabel} text:${it.text}")
            translateUseCase(domainModel)
                .toObservable()
                .observeOn(schedulerProvider.ui())
                .doOnError { error ->
                    viewState.showErrorState(errorStateMapper(error.message))
                }.observeOn(schedulerProvider.io())
                .onExceptionResumeNext {}
        }
        .map { WordStateModel(text = it.translation, languageLabel = it.toLanguageLabel) }
        .observeOn(schedulerProvider.ui())
        .subscribe {
            currentTranslation = it
            viewState.showTranslation(
                translation = it.text,
                shouldBlockTranslateButton = fromLanguageLabel == toLanguageLabel
                        || it.text.isEmpty()
            )
        }.also { addToDisposables(it) }

    private fun showSuccessState() {
        val successState = successStateMapper()
        fromLanguageLabel = successState.fromLanguageLabels.first().toString()
        toLanguageLabel = successState.toLanguageLabels.first().toString()
        currentInputText = WordStateModel(text = "", languageLabel = fromLanguageLabel)
        fromLanguageLabels = successState.fromLanguageLabels.toMutableList()
        toLanguageLabels = successState.toLanguageLabels.toMutableList()
        viewState.showSuccessState(successState)
    }

    companion object {
        val TAG: String = AddTranslationPresenter::class.java.simpleName
    }
}