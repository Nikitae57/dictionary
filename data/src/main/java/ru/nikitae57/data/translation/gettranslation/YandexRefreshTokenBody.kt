package ru.nikitae57.data.translation.gettranslation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YandexRefreshTokenBody(
    @field:Json(name = "yandexPassportOauthToken") val oAuthToken: String
)