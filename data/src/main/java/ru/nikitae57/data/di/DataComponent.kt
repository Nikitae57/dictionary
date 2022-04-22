package ru.nikitae57.data.di

import dagger.Component
import retrofit2.Retrofit
import ru.nikitae57.common.di.CommonComponent
import ru.nikitae57.domain.core.token.TokenProvider
import ru.nikitae57.domain.core.token.TokenSource
import ru.nikitae57.domain.core.token.TokenStore
import javax.inject.Scope
import javax.inject.Singleton

@Scope
annotation class DataScope

@Singleton
@Component(
    dependencies = [CommonComponent::class],
    modules = [DataModule::class, DataBindingModule::class]
)
interface DataComponent {
    fun retrofit(): Retrofit

    fun tokenStore(): TokenStore

    fun tokenProvider(): TokenProvider

    fun tokenSource(): TokenSource
}