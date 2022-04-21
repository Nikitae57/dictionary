package ru.nikitae57.domain.translation.translate

import io.reactivex.Single
import javax.inject.Inject

class TranslateUseCase @Inject constructor(
    private val translationSource: TranslationSource
) {
    operator fun invoke(wordToTranslateDomainModel: WordToTranslateDomainModel): Single<TranslationDomainModel> {
        if (wordToTranslateDomainModel.fromLanguageLabel == wordToTranslateDomainModel.toLanguageLabel) {
            return Single.just(
                TranslationDomainModel(
                    translation = wordToTranslateDomainModel.text,
                    originalText = wordToTranslateDomainModel.text,
                    fromLanguageLabel = wordToTranslateDomainModel.fromLanguageLabel,
                    toLanguageLabel = wordToTranslateDomainModel.toLanguageLabel
                )
            )
        }

        return translationSource.getTranslation(
            text = wordToTranslateDomainModel.text,
            fromLanguageLabel = wordToTranslateDomainModel.fromLanguageLabel,
            toLanguageLabel = wordToTranslateDomainModel.toLanguageLabel
        )
    }
}