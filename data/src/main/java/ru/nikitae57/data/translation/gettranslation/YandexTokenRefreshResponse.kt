package ru.nikitae57.data.translation.gettranslation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YandexTokenRefreshResponse(
    @field:Json(name = "iamToken") val token: String,
    @field:Json(name = "expiresAt") val expiresAt: String
)