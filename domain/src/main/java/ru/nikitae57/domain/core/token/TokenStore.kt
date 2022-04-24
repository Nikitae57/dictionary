package ru.nikitae57.domain.core.token

interface TokenStore {
    fun setToken(token: String)
}