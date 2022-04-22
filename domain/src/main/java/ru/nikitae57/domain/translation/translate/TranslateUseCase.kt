package ru.nikitae57.domain.translation.translate

import io.reactivex.Single
import javax.inject.Inject

class TranslateUseCase @Inject constructor(
    private val translationSource: TranslationSource
) {
    operator fun invoke(textToTranslateDomainModel: TextToTranslateDomainModel): Single<TranslationDomainModel> {
        if (textToTranslateDomainModel.fromLanguageLabel == textToTranslateDomainModel.toLanguageLabel) {
            return Single.just(
                TranslationDomainModel(
                    translation = textToTranslateDomainModel.text,
                    originalText = textToTranslateDomainModel.text,
                    fromLanguageLabel = textToTranslateDomainModel.fromLanguageLabel,
                    toLanguageLabel = textToTranslateDomainModel.toLanguageLabel
                )
            )
        }

        return translationSource.getTranslation(textToTranslateDomainModel)
    }
}