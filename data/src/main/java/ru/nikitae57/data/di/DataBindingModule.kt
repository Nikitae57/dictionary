package ru.nikitae57.data.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.data.translation.gettranslation.TranslationStubDataSource
import ru.nikitae57.data.translation.savedtranslations.SavedTranslationsDataSource
import ru.nikitae57.domain.translation.savedtranslations.SavedTranslationsSource
import ru.nikitae57.domain.translation.translate.TranslationSource

@Module
abstract class DataBindingModule {
    @Binds
    abstract fun bindTranslationSource(translationStubDataSource: TranslationStubDataSource): TranslationSource

    @Binds
    abstract fun bindSavedTranslationsSource(savedTranslationsDataSource: SavedTranslationsDataSource): SavedTranslationsSource
}