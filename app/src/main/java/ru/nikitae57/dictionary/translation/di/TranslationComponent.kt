package ru.nikitae57.dictionary.translation.di

import dagger.Component
import ru.nikitae57.data.di.DataBindingModule
import ru.nikitae57.dictionary.di.ActivityScope
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.translation.mainscreen.MainScreenFragment
import ru.nikitae57.dictionary.translation.mainscreen.MainScreenPresenter
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.translation.translate.TranslateUseCase

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DataBindingModule::class]
)
interface TranslationComponent {
    fun resources(): Res

    fun mainScreenPresenter(): MainScreenPresenter

    fun translateUseCase(): TranslateUseCase

    fun inject(mainScreenFragment: MainScreenFragment)
}