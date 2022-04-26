package ru.nikitae57.data.core.token

import io.reactivex.Single
import ru.nikitae57.data.BuildConfig
import ru.nikitae57.data.translation.gettranslation.YandexRefreshTokenBody
import ru.nikitae57.data.translation.gettranslation.YandexTokenApi
import ru.nikitae57.domain.core.token.TokenSource
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val tokenApi: YandexTokenApi
) : TokenSource {
    override fun getToken(): Single<String> {
        val body = YandexRefreshTokenBody(BuildConfig.API_KEY)
        return tokenApi.refreshToken(body)
            .map { it.token }
    }
}