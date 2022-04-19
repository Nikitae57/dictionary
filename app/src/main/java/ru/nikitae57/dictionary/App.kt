package ru.nikitae57.dictionary

import android.app.Application
import ru.nikitae57.dictionary.di.AppComponent
import ru.nikitae57.dictionary.di.AppModule
import ru.nikitae57.dictionary.di.DaggerAppComponent

open class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}