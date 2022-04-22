package ru.nikitae57.data.translation.gettranslation

import ru.nikitae57.domain.translation.translate.TextToTranslateDomainModel
import ru.nikitae57.domain.translation.translate.TranslationDomainModel
import javax.inject.Inject

class TranslationDomainModelMapper @Inject constructor() {
    operator fun invoke(
        textToTranslateDomainModel: TextToTranslateDomainModel,
        translationsDataModel: TranslationsDataModel
    ): TranslationDomainModel {
        return TranslationDomainModel(
            translation = translationsDataModel.translations.first().text,
            originalText = textToTranslateDomainModel.text,
            fromLanguageLabel = textToTranslateDomainModel.fromLanguageLabel,
            toLanguageLabel = textToTranslateDomainModel.toLanguageLabel
        )
    }
}