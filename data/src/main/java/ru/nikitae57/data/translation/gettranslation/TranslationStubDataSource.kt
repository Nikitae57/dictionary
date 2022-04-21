package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.translate.TranslationDomainModel
import ru.nikitae57.domain.translation.translate.TranslationSource
import javax.inject.Inject

class TranslationStubDataSource @Inject constructor() : TranslationSource {
    override fun getTranslation(
        text: String,
        fromLanguageLabel: String,
        toLanguageLabel: String
    ): Single<TranslationDomainModel> {
        val translation = when (toLanguageLabel) {
            "ru" -> "Немного русского текста"
            "en" -> "Some English text"
            "fr" -> "Un peu de texte français"
            else -> throw Exception("Unsupported language: $toLanguageLabel")
        }

        return Single.just(
            TranslationDomainModel(
                translation = translation,
                originalText = text,
                fromLanguageLabel = fromLanguageLabel,
                toLanguageLabel = toLanguageLabel
            )
        )
    }
}