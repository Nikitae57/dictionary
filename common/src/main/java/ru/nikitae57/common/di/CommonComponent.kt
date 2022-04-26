package ru.nikitae57.common.di

import android.app.Application
import android.content.Context
import dagger.Component
import ru.nikitae57.domain.core.SchedulerProvider

@Component(modules = [CommonBindingModule::class, CommonModule::class])
interface CommonComponent {
    fun schedulerProvider(): SchedulerProvider

    fun context(): Context

    fun application(): Application
}