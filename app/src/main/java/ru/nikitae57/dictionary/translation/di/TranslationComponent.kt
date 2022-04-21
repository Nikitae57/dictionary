package ru.nikitae57.dictionary.translation.di

import dagger.Component
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment
import javax.inject.Scope

@Scope
annotation class TranslationScope

@TranslationScope
@Component(
    dependencies = [AppComponent::class],
    modules = [TranslationBindingModule::class, TranslationModule::class]
)
interface TranslationComponent {
    fun inject(savedTranslationsFragment: SavedTranslationsFragment)

    fun inject(addTranslationFragment: AddTranslationFragment)
}