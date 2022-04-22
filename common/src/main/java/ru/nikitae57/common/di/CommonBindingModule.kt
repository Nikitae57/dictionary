package ru.nikitae57.common.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.common.AppSchedulerProvider
import ru.nikitae57.domain.core.SchedulerProvider

@Module
abstract class CommonBindingModule {
    @Binds
    abstract fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider
}