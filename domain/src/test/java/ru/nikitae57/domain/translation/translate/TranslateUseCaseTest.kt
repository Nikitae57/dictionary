package ru.nikitae57.domain.translation.translate

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import ru.nikitae57.domain.translation.models.LanguagesDomainModel

private val translationDomainModel = TranslationDomainModel(translation = "translation")
private const val text = "text"
private val fromLanguage = LanguagesDomainModel.FR
private val toLanguage = LanguagesDomainModel.EN

class TranslateUseCaseTest {
    private val translationSource = mock<TranslationSource> {
        on { getTranslation(any(), any(), any()) } doReturn Single.just(translationDomainModel)
    }

    private val useCase = TranslateUseCase(translationSource = translationSource)

    @Test
    fun `GIVEN source succeed WHEN calling use case THEN should return result`() {
        val observer = TestObserver<TranslationDomainModel>()
        useCase(text = text, fromLanguage = fromLanguage, toLanguage = toLanguage)
            .toObservable()
            .subscribe(observer)

        observer.assertValue(translationDomainModel)
    }

    @Test
    fun `GIVEN to and from languages match WHEN calling use case THEN should return passed text`() {
        val observer = TestObserver<TranslationDomainModel>()
        useCase(text = text, fromLanguage = fromLanguage, toLanguage = fromLanguage)
            .toObservable()
            .subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = text))
        verifyNoInteractions(translationSource)
    }

    @Test
    fun `GIVEN source fails WHEN calling use case THEN should return error`() {
        val error = Exception()
        whenever(translationSource.getTranslation(text = text, fromLanguage = fromLanguage, toLanguage = toLanguage))
            .thenReturn(Single.error(error))

        val observer = TestObserver<TranslationDomainModel>()
        useCase(text = text, fromLanguage = fromLanguage, toLanguage = toLanguage)
            .toObservable()
            .subscribe(observer)

        observer.assertError(error)
    }
}