package ru.nikitae57.dictionary

import android.app.Application
import ru.nikitae57.common.di.CommonComponent
import ru.nikitae57.common.di.DaggerCommonComponent
import ru.nikitae57.data.di.DaggerDataComponent
import ru.nikitae57.data.di.DataComponent
import ru.nikitae57.data.di.DataModule
import ru.nikitae57.data.translation.di.DaggerTranslationDataComponent
import ru.nikitae57.data.translation.di.TranslationDataModule
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.di.AppModule
import ru.nikitae57.dictionary.di.DaggerAppComponent
import ru.nikitae57.dictionary.translation.di.DaggerTranslationComponent
import ru.nikitae57.dictionary.translation.di.TranslationComponent
import ru.nikitae57.dictionary.translation.di.TranslationModule

open class App : Application() {
    private val commonComponent: CommonComponent by lazy {
        DaggerCommonComponent.builder()
            .build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .commonComponent(commonComponent)
            .dataComponent(dataComponent)
            .build()
    }

    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent.builder()
            .commonComponent(commonComponent)
            .dataModule(DataModule())
            .build()
    }

    // I should've created another "translation" activity and store component there,
    // but I'm too lazy, so I'll leave it here
    val translationComponent: TranslationComponent by lazy {
        val translationDataComponent = DaggerTranslationDataComponent.builder()
            .translationDataModule(TranslationDataModule())
            .dataComponent(dataComponent)
            .build()

        DaggerTranslationComponent.builder()
            .appComponent(appComponent)
            .translationDataComponent(translationDataComponent)
            .translationModule(TranslationModule())
            .build()
    }
}