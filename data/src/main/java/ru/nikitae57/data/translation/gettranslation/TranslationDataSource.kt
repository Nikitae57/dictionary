package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.translate.TextToTranslateDomainModel
import ru.nikitae57.domain.translation.translate.TranslationDomainModel
import ru.nikitae57.domain.translation.translate.TranslationSource
import javax.inject.Inject

class TranslationDataSource @Inject constructor(
    private val translationApi: TranslationApi,
    private val translationDomainModelMapper: TranslationDomainModelMapper,
    private val textToTranslateDataModelMapper: TextToTranslateDataModelMapper,
) : TranslationSource {
    override fun getTranslation(textToTranslateDomainModel: TextToTranslateDomainModel): Single<TranslationDomainModel> {
        val textToTranslateDataModel = textToTranslateDataModelMapper(textToTranslateDomainModel)
        return translationApi.translate(textToTranslateDataModel).map {
            translationDomainModelMapper(textToTranslateDomainModel, it)
        }
    }
}