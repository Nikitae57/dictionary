package ru.nikitae57.domain.translation.savedtranslations

import io.reactivex.Single
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel

interface SavedTranslationsSource {
    fun getSavedTranslations(): Single<DictionaryEntriesDomainModel>
}