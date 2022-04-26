package ru.nikitae57.common.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import ru.nikitae57.common.AppSchedulerProvider
import ru.nikitae57.domain.core.SchedulerProvider

@Module
abstract class CommonBindingModule {
    @Binds
    abstract fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    abstract fun bindContext(app: Application): Context
}