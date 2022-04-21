package ru.nikitae57.dictionary.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nikitae57.dictionary.core.room.APP_DB_NAME
import ru.nikitae57.dictionary.core.room.AppDatabase
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesDataBase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, APP_DB_NAME)
            .build()
}