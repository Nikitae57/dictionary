package ru.nikitae57.dictionary.di

import android.content.Context
import com.github.terrakok.cicerone.Router
import dagger.Component
import ru.nikitae57.common.di.AppScope
import ru.nikitae57.common.di.CommonComponent
import ru.nikitae57.common.di.CommonModule
import ru.nikitae57.data.di.DataComponent
import ru.nikitae57.dictionary.MainActivity
import ru.nikitae57.dictionary.launcher.LauncherActivity
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.core.token.TokenProvider
import ru.nikitae57.domain.core.token.TokenStore
import ru.nikitae57.domain.di.DomainModule

@AppScope
@Component(
    dependencies = [CommonComponent::class, DataComponent::class],
    modules = [
        CommonModule::class,
        AppBindingModule::class,
        NavigationModule::class,
        DomainModule::class,
    ]
)
interface AppComponent {
    fun context(): Context

    fun resources(): Res

    fun router(): Router

    fun schedulerProvider(): SchedulerProvider

    fun tokenStore(): TokenStore

    fun tokenProvider(): TokenProvider

    fun inject(mainActivity: MainActivity)

    fun inject(launcherActivity: LauncherActivity)
}