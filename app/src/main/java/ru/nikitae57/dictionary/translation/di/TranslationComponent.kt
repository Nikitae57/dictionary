package ru.nikitae57.dictionary.translation.di

import dagger.Component
import ru.nikitae57.data.di.DataBindingModule
import ru.nikitae57.dictionary.di.ActivityScope
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsPresenter
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.translation.translate.TranslateUseCase

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DataBindingModule::class]
)
interface TranslationComponent {
    fun resources(): Res

    fun mainScreenPresenter(): SavedTranslationsPresenter

    fun translateUseCase(): TranslateUseCase

    fun inject(savedTranslationsFragment: SavedTranslationsFragment)

    fun inject(addTranslationFragment: AddTranslationFragment)
}