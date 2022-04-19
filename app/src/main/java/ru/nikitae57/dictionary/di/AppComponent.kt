package ru.nikitae57.dictionary.di

import android.content.Context
import com.github.terrakok.cicerone.Router
import dagger.Component
import ru.nikitae57.data.di.DataBindingModule
import ru.nikitae57.dictionary.MainActivity
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.di.DomainBindingModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppBindingModule::class,
        NavigationModule::class,
        DomainBindingModule::class,
    ]
)
interface AppComponent {
    fun context(): Context

    fun resources(): Res

    fun router(): Router

    fun inject(mainActivity: MainActivity)
}