package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.models.LanguagesDomainModel
import ru.nikitae57.domain.translation.translate.TranslationDomainModel
import ru.nikitae57.domain.translation.translate.TranslationSource
import javax.inject.Inject

class TranslationStubDataSource @Inject constructor() : TranslationSource {
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