package ru.nikitae57.dictionary.translation.savedtranslations

import io.reactivex.Completable
import io.reactivex.Single
import ru.nikitae57.dictionary.core.room.translation.TranslationDao
import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import javax.inject.Inject

class SavedTranslationsDataSource @Inject constructor(
    private val translationsDao: TranslationDao,
    private val dictionaryEntryDomainModelMapper: DictionaryEntryDomainModelMapper,
    private val dictionaryEntriesDataModelMapper: DictionaryEntriesDataModelMapper
) : SavedTranslationsSource {
    override fun getSavedTranslations(): Single<DictionaryEntriesDomainModel> {
        return translationsDao.getDictionaryEntries()
            .map {
                dictionaryEntryDomainModelMapper(it)
            }
    }

    override fun saveTranslation(dictionaryEntryDomainModel: DictionaryEntryDomainModel): Completable {
        return translationsDao.insertDictionaryEntries(dictionaryEntriesDataModelMapper(dictionaryEntryDomainModel))
            .ignoreElement()
    }
}