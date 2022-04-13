package ru.nikitae57.domain.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.LanguagesDomainModel

interface TranslationSource {
    fun getTranslation(
        text: String,
        fromLanguage: LanguagesDomainModel,
        toLanguage: LanguagesDomainModel
    ): Single<TranslationDomainModel>
}