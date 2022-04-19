package ru.nikitae57.data.translation.savedtranslations

import io.reactivex.Single
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.LanguagesDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import ru.nikitae57.domain.translation.savedtranslation.SavedTranslationsSource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SavedTranslationsDataSource @Inject constructor() : SavedTranslationsSource {
    override fun getSavedTranslations(): Single<DictionaryEntriesDomainModel> {
        return Single.just(
            DictionaryEntriesDomainModel(
                entries = listOf(
                    DictionaryEntryDomainModel(
                        words = listOf(
                            WordDomainModel(text = "Слово", language = LanguagesDomainModel.RU),
                            WordDomainModel(text = "Word", language = LanguagesDomainModel.EN),
                        )
                    ),
                    DictionaryEntryDomainModel(
                        words = listOf(
                            WordDomainModel(text = "Что-то", language = LanguagesDomainModel.RU),
                            WordDomainModel(text = "Something", language = LanguagesDomainModel.EN),
                            WordDomainModel(text = "Que", language = LanguagesDomainModel.FR),
                        )
                    )
                )
            )
        )
    }
}