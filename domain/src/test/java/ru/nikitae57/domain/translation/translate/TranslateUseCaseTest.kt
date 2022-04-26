package ru.nikitae57.domain.translation.translate

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test

private val translationDomainModel = TranslationDomainModel(
    translation = "translation",
    originalText = "originalText",
    fromLanguageLabel = "fromLanguageLabel",
    toLanguageLabel = "toLanguageLabel"
)
private const val text = "text"
private const val fromLanguageLabel = "fromLanguageLabel"
private const val toLanguageLabel = "toLanguageLabel"

class TranslateUseCaseTest {
    private val translationSource = mockk<TranslationSource> {
        every { getTranslation(any()) } returns Single.just(translationDomainModel)
    }

    private val useCase = TranslateUseCase(translationSource = translationSource)

    @Test
    fun `GIVEN source succeed WHEN calling use case THEN should return result`() {
        val observer = TestObserver<TranslationDomainModel>()
        useCase(TextToTranslateDomainModel(text = text, fromLanguageLabel = fromLanguageLabel, toLanguageLabel = toLanguageLabel))
            .subscribe(observer)

        observer.assertValue(translationDomainModel)
    }

    @Test
    fun `GIVEN to and from languages match WHEN calling use case THEN should return passed text`() {
        val observer = TestObserver<TranslationDomainModel>()
        useCase(TextToTranslateDomainModel(text = text, fromLanguageLabel = fromLanguageLabel, toLanguageLabel = fromLanguageLabel))
            .subscribe(observer)

        observer.assertValue(
            TranslationDomainModel(
                translation = text,
                originalText = text,
                fromLanguageLabel = fromLanguageLabel,
                toLanguageLabel = fromLanguageLabel
            )
        )
        verify(exactly = 0) { translationSource.getTranslation(any()) }
    }

    @Test
    fun `GIVEN source fails WHEN calling use case THEN should return error`() {
        val error = Exception()
        every { translationSource.getTranslation(any()) } returns Single.error(error)

        val observer = TestObserver<TranslationDomainModel>()
        useCase(
            TextToTranslateDomainModel(
                text = text,
                fromLanguageLabel = fromLanguageLabel,
                toLanguageLabel = toLanguageLabel
            )
        ).subscribe(observer)

        observer.assertError(error)
    }
}