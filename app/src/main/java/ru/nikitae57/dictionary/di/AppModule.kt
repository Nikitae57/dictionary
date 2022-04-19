package ru.nikitae57.dictionary.di

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val application: Application) {
    @Provides
    @Singleton
    fun providesApplication() = application
}