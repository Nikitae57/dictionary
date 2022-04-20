package ru.nikitae57.data.translation.gettranslation

import io.reactivex.observers.TestObserver
import org.junit.Test
import ru.nikitae57.domain.translation.translate.TranslationDomainModel

private const val text = "text"
private const val fromLanguageLabel = "fr"

class TranslationStubDataSourceTest {
    private val translationSource = TranslationStubDataSource()
    
    @Test
    fun `GIVEN french language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguageLabel = fromLanguageLabel,
            toLanguageLabel = "fr"
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Un peu de texte français"))
    }

    @Test
    fun `GIVEN russian language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguageLabel = fromLanguageLabel,
            toLanguageLabel = "ru"
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Немного русского текста"))
    }

    @Test
    fun `GIVEN english language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguageLabel = fromLanguageLabel,
            toLanguageLabel = "en"
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Some English text"))
    }
}