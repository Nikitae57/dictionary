package ru.nikitae57.domain.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.LanguagesDomainModel
import javax.inject.Inject

class GetTranslationUseCase @Inject constructor(
    private val translationSource: TranslationSource
) {
    fun invoke(
        text: String,
        fromLanguage: LanguagesDomainModel,
        toLanguage: LanguagesDomainModel
    ): Single<TranslationDomainModel> = translationSource.getTranslation(
        text = text,
        fromLanguage = fromLanguage,
        toLanguage = toLanguage
    )
}