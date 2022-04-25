package ru.nikitae57.data.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nikitae57.data.core.room.translation.DictionaryEntryDataModel
import ru.nikitae57.data.core.room.translation.TranslationDao
import ru.nikitae57.data.core.room.translation.WordDataModel

const val APP_DB_NAME = "app_database"

@Database(
    version = 1,
    entities = [
        DictionaryEntryDataModel::class,
        WordDataModel::class
    ],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun translationDao(): TranslationDao
}