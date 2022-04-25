package ru.nikitae57.dictionary.translation.di

import dagger.Component
import ru.nikitae57.common.di.TranslationScope
import ru.nikitae57.data.translation.di.TranslationDataComponent
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment

@TranslationScope
@Component(
    dependencies = [AppComponent::class, TranslationDataComponent::class],
)
interface TranslationComponent {
    fun inject(savedTranslationsFragment: SavedTranslationsFragment)

    fun inject(addTranslationFragment: AddTranslationFragment)
}