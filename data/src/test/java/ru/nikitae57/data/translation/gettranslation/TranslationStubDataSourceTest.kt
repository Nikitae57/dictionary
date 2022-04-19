package ru.nikitae57.data.translation.gettranslation

import io.reactivex.observers.TestObserver
import org.junit.Test
import ru.nikitae57.domain.translation.models.LanguagesDomainModel
import ru.nikitae57.domain.translation.translate.TranslationDomainModel

private const val text = "text"
private val fromLanguage = LanguagesDomainModel.FR

class TranslationStubDataSourceTest {
    private val translationSource = TranslationStubDataSource()
    
    @Test
    fun `GIVEN french language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguage = fromLanguage,
            toLanguage = LanguagesDomainModel.FR
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Un peu de texte français"))
    }

    @Test
    fun `GIVEN russian language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguage = fromLanguage,
            toLanguage = LanguagesDomainModel.RU
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Немного русского текста"))
    }

    @Test
    fun `GIVEN english language WHEN invoking translation source THEN should return right translation`() {
        val observer = TestObserver<TranslationDomainModel>()
        translationSource.getTranslation(
            text = text, fromLanguage = fromLanguage,
            toLanguage = LanguagesDomainModel.EN
        ).subscribe(observer)

        observer.assertValue(TranslationDomainModel(translation = "Some English text"))
    }
}