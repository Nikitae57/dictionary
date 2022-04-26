package ru.nikitae57.data.core.room.translation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@Suppress("FunctionName")
@Dao
abstract class TranslationDao {
    @Transaction
    @Query("SELECT * from ${DictionaryEntryDataModel.TABLE_NAME}")
    abstract fun getDictionaryEntries(): Single<List<DictionaryEntryWithWordsDataModel>>

    @Insert
    abstract fun insertDictionary(dictionaryEntryDataModel: DictionaryEntryDataModel): Single<Long>

    @Insert
    abstract fun _insertWords(words: List<WordDataModel>): Completable

    fun insertDictionaryEntries(dictionaryEntriesDataModel: DictionaryEntriesDataModel) =
        insertDictionary(DictionaryEntryDataModel())
            .subscribeOn(Schedulers.io())
            .map { dictionaryId ->
                dictionaryEntriesDataModel.words.map { wordDataModel ->
                    wordDataModel.dictionaryId = dictionaryId
                }
                dictionaryEntriesDataModel.words
            }.flatMap {
                _insertWords(it).andThen(Single.just(Unit))
            }
}