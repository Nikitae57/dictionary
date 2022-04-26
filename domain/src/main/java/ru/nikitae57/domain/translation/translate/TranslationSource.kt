package ru.nikitae57.domain.translation.translate

import io.reactivex.Single

interface TranslationSource {
    fun getTranslation(textToTranslateDomainModel: TextToTranslateDomainModel): Single<TranslationDomainModel>
}