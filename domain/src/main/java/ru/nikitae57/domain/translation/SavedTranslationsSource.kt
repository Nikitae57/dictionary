package ru.nikitae57.domain.translation

import io.reactivex.Completable
import io.reactivex.Single
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel

interface SavedTranslationsSource {
    fun getSavedTranslations(): Single<DictionaryEntriesDomainModel>

    fun saveTranslation(wordDomainModel: WordDomainModel): Completable
}