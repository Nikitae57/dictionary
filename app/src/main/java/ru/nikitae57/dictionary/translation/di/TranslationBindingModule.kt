package ru.nikitae57.dictionary.translation.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsDataSource
import ru.nikitae57.domain.translation.SavedTranslationsSource

@Module
abstract class TranslationBindingModule {
    @Binds
    abstract fun bindSavedTranslationsSource(savedTranslationsDataSource: SavedTranslationsDataSource): SavedTranslationsSource
}