package ru.nikitae57.domain.translation.translate

import io.reactivex.Single

interface TranslationSource {
    fun getTranslation(
        text: String,
        fromLanguageLabel: String,
        toLanguageLabel: String
    ): Single<TranslationDomainModel>
}