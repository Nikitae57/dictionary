package ru.nikitae57.domain.translation.gettranslation

import io.reactivex.Single
import ru.nikitae57.domain.translation.LanguagesDomainModel
import javax.inject.Inject

class GetTranslationUseCase @Inject constructor(
    private val translationSource: TranslationSource
) {
    operator fun invoke(
        text: String,
        fromLanguage: LanguagesDomainModel,
        toLanguage: LanguagesDomainModel
    ): Single<TranslationDomainModel> {
        if (fromLanguage == toLanguage) {
            return Single.just(TranslationDomainModel(translation = text))
        }

        return translationSource.getTranslation(
            text = text,
            fromLanguage = fromLanguage,
            toLanguage = toLanguage
        )
    }
}