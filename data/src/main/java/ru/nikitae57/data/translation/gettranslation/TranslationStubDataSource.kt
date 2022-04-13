package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.LanguagesDomainModel
import ru.nikitae57.domain.translation.gettranslation.TranslationDomainModel
import ru.nikitae57.domain.translation.gettranslation.TranslationSource


class TranslationStubDataSource : TranslationSource {
    override fun getTranslation(
        text: String,
        fromLanguage: LanguagesDomainModel,
        toLanguage: LanguagesDomainModel
    ): Single<TranslationDomainModel> {
        val translation = when(toLanguage) {
            LanguagesDomainModel.RU -> "Немного русского текста"
            LanguagesDomainModel.EN -> "Some English text"
            LanguagesDomainModel.FR -> "Un peu de texte français"
        }

        return Single.just(TranslationDomainModel(translation))
    }
}