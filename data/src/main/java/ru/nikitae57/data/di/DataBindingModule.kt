package ru.nikitae57.data.di

import dagger.Binds
import dagger.Module
import ru.nikitae57.data.core.token.TokenDataProvider
import ru.nikitae57.data.core.token.TokenDataSource
import ru.nikitae57.domain.core.token.TokenProvider
import ru.nikitae57.domain.core.token.TokenSource
import ru.nikitae57.domain.core.token.TokenStore

@Module
abstract class DataBindingModule {
    @Binds
    abstract fun bindTokenStore(tokenDataProvider: TokenDataProvider): TokenStore

    @Binds
    abstract fun bindTokenProvider(tokenDataProvider: TokenDataProvider): TokenProvider

    @Binds
    abstract fun bindTokenSource(tokenDataSource: TokenDataSource): TokenSource
}