package ru.nikitae57.data.translation.gettranslation

import ru.nikitae57.data.BuildConfig
import ru.nikitae57.domain.translation.translate.TextToTranslateDomainModel
import javax.inject.Inject

class TextToTranslateDataModelMapper @Inject constructor() {
    operator fun invoke(textToTranslateDomainModel: TextToTranslateDomainModel) = TextToTranslateDataModel(
        texts = listOf(textToTranslateDomainModel.text),
        folderId = BuildConfig.FOLDER_ID,
        targetLanguageCode = textToTranslateDomainModel.toLanguageLabel
    )
}