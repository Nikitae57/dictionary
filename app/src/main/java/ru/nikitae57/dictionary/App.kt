package ru.nikitae57.dictionary

import android.app.Application
import ru.nikitae57.common.di.CommonComponent
import ru.nikitae57.common.di.CommonModule
import ru.nikitae57.common.di.DaggerCommonComponent
import ru.nikitae57.data.di.DaggerDataComponent
import ru.nikitae57.data.di.DataComponent
import ru.nikitae57.data.di.DataModule
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.di.DaggerAppComponent

open class App : Application() {
    private val commonComponent: CommonComponent by lazy {
        DaggerCommonComponent.builder()
            .commonModule(CommonModule(application = this))
            .build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .commonComponent(commonComponent)
            .dataComponent(dataComponent)
            .build()
    }

    val dataComponent: DataComponent by lazy {
        DaggerDataComponent.builder()
            .commonComponent(commonComponent)
            .dataModule(DataModule())
            .build()
    }
}