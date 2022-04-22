package ru.nikitae57.dictionary.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.nikitae57.common.di.AppScope
import ru.nikitae57.dictionary.core.room.APP_DB_NAME
import ru.nikitae57.dictionary.core.room.AppDatabase

@Module
class AppModule(private val application: Application) {
    @Provides
    @AppScope
    fun providesApplication() = application

    @Provides
    @AppScope
    fun providesDataBase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, APP_DB_NAME)
            .build()
}