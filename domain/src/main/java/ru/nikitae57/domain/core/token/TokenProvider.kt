package ru.nikitae57.domain.core.token

interface TokenProvider {
    fun getToken(): String
}