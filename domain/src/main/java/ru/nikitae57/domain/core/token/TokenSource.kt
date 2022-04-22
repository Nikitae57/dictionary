package ru.nikitae57.domain.core.token

import io.reactivex.Single

interface TokenSource {
    fun getToken(): Single<String>
}