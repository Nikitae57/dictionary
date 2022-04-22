package ru.nikitae57.common.di

import dagger.Component
import ru.nikitae57.domain.core.SchedulerProvider

@Component(modules = [CommonBindingModule::class])
interface CommonComponent {
    fun schedulerProvider(): SchedulerProvider
}