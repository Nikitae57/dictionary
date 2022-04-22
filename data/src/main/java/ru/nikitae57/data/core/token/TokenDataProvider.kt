package ru.nikitae57.data.core.token

import ru.nikitae57.domain.core.token.TokenProvider
import ru.nikitae57.domain.core.token.TokenStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenDataProvider @Inject constructor() : TokenStore, TokenProvider {
    private var token = ""

    override fun getToken(): String {
        return token
    }

    override fun storeToken(token: String) {
        this.token = token
    }
}