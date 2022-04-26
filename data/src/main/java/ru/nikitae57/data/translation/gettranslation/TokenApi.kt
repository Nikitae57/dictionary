package ru.nikitae57.data.translation.gettranslation

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface YandexTokenApi {
    @POST
    fun refreshToken(
        @Body body: YandexRefreshTokenBody,
        @Url refreshTokenApiUrl: String = "https://iam.api.cloud.yandex.net/iam/v1/tokens"
    ): Single<YandexTokenRefreshResponse>
}