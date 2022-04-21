package ru.nikitae57.dictionary.translation.di

import dagger.Module
import dagger.Provides
import ru.nikitae57.dictionary.core.room.AppDatabase

@Module
class TranslationModule {
    @Provides
    fun providesTranslationDao(appDatabase: AppDatabase) = appDatabase.translationDao()
}