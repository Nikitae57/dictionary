package ru.nikitae57.dictionary.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.nikitae57.common.di.AppScope

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @AppScope
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @AppScope
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}
