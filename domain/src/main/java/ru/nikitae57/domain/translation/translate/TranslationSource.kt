package ru.nikitae57.domain.translation.translate

import io.reactivex.Single
import ru.nikitae57.domain.translation.models.LanguagesDomainModel

interface TranslationSource {
    fun getTranslation(
        text: String,
        fromLanguage: LanguagesDomainModel,
        toLanguage: LanguagesDomainModel
    ): Single<TranslationDomainModel>
}