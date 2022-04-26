package ru.nikitae57.common.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class CommonModule(private val application: Application) {
    @Provides
    fun providesApplication() = application
}