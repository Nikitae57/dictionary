package ru.nikitae57.data.translation.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.nikitae57.data.core.room.AppDatabase
import ru.nikitae57.data.translation.gettranslation.TranslationApi

@Module
class TranslationDataModule {
    @Provides
    fun providesTranslationApi(retrofit: Retrofit): TranslationApi = retrofit.create(TranslationApi::class.java)

    @Provides
    fun providesTranslationDao(appDatabase: AppDatabase) = appDatabase.translationDao()
}