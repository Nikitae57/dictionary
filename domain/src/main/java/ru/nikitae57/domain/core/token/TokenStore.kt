package ru.nikitae57.domain.core.token

interface TokenStore {
    fun storeToken(token: String)
}