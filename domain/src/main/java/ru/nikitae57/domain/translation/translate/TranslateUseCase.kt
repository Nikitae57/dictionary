package ru.nikitae57.domain.translation.translate

import io.reactivex.Single
import javax.inject.Inject

class TranslateUseCase @Inject constructor(
    private val translationSource: TranslationSource
) {
    operator fun invoke(
        text: String,
        fromLanguageLabel: String,
        toLanguageLabel: String
    ): Single<TranslationDomainModel> {
        if (fromLanguageLabel == toLanguageLabel) {
            return Single.just(TranslationDomainModel(translation = text))
        }

        return translationSource.getTranslation(
            text = text,
            fromLanguageLabel = fromLanguageLabel,
            toLanguageLabel = toLanguageLabel
        )
    }
}