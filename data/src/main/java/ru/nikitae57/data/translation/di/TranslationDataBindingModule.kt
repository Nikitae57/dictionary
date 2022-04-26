package ru.nikitae57.data.translation.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.data.core.room.translation.savedtranslations.SavedTranslationsDataSource
import ru.nikitae57.data.translation.gettranslation.TranslationDataSource
import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.translate.TranslationSource

@Module
abstract class TranslationDataBindingModule {
    @Binds
    abstract fun bindTranslationSource(translationDataSource: TranslationDataSource): TranslationSource

    @Binds
    abstract fun bindSavedTranslationsSource(savedTranslationsDataSource: SavedTranslationsDataSource): SavedTranslationsSource
}