package ru.nikitae57.data.translation.di

import dagger.Component
import retrofit2.Retrofit
import ru.nikitae57.data.di.DataComponent
import ru.nikitae57.domain.core.token.TokenProvider
import ru.nikitae57.domain.core.token.TokenSource
import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.translate.TranslationSource
import javax.inject.Scope

@Scope
annotation class TranslationDataScope

@TranslationDataScope
@Component(
    dependencies = [DataComponent::class],
    modules = [TranslationDataModule::class, TranslationDataBindingModule::class]
)
interface TranslationDataComponent {
    fun retrofit(): Retrofit

    fun translationSource(): TranslationSource

    fun savedTranslationsSource(): SavedTranslationsSource

    fun tokenProvider(): TokenProvider

    fun tokenSource(): TokenSource
}