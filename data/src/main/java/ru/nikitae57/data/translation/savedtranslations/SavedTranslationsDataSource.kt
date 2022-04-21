package ru.nikitae57.data.translation.savedtranslations

import io.reactivex.Completable
import io.reactivex.Single
import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class SavedTranslationsDataSource @Inject constructor() : SavedTranslationsSource {
    override fun getSavedTranslations(): Single<DictionaryEntriesDomainModel> {
        return Single.just(
            DictionaryEntriesDomainModel(
                entries = listOf(
                    DictionaryEntryDomainModel(
                        words = listOf(
                            WordDomainModel(text = "Слово", languageLabel = "ru"),
                            WordDomainModel(text = "Word", languageLabel = "en"),
                        )
                    ),
                    DictionaryEntryDomainModel(
                        words = listOf(
                            WordDomainModel(text = "Что-то", languageLabel = "ru"),
                            WordDomainModel(text = "Something", languageLabel = "en"),
                            WordDomainModel(text = "Que", languageLabel = "fr"),
                        )
                    )
                )
            )
        )
    }

    override fun saveTranslation(dictionaryEntryDomainModel: DictionaryEntryDomainModel): Completable {
        return Completable.complete()
    }
}