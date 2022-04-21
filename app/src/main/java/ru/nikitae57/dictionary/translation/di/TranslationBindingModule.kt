package ru.nikitae57.dictionary.translation.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.data.translation.gettranslation.TranslationStubDataSource
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsDataSource
import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.translate.TranslationSource

@Module
abstract class TranslationBindingModule {
    @Binds
    abstract fun bindSavedTranslationsSource(savedTranslationsDataSource: SavedTranslationsDataSource): SavedTranslationsSource

    @Binds
    abstract fun bindTranslationSource(translationStubDataSource: TranslationStubDataSource): TranslationSource
}