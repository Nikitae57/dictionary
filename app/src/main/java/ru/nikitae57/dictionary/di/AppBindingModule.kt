package ru.nikitae57.dictionary.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import ru.nikitae57.dictionary.core.AppResources
import ru.nikitae57.dictionary.core.AppSchedulerProvider
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.core.SchedulerProvider
import javax.inject.Singleton

@Module
abstract class AppBindingModule {
    @Binds
    @Singleton
    abstract fun bindContext(app: Application): Context

    @Binds
    @Singleton
    abstract fun bindResources(appResources: AppResources): Res

    @Binds
    @Singleton
    abstract fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider
}