package ru.nikitae57.domain.di

import dagger.Module
import dagger.Provides
import ru.nikitae57.domain.translation.translate.TranslateUseCase
import ru.nikitae57.domain.translation.translate.TranslationSource

@Module
class DomainBindingModule {
    @Provides
    fun translateUseCase(translationSource: TranslationSource) = TranslateUseCase(translationSource)
}