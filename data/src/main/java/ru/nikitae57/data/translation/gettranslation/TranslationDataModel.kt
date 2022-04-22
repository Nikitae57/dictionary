package ru.nikitae57.data.translation.gettranslation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TranslationsDataModel(
    @field:Json(name = "translations") val translations: List<TranslationDataModel>
)

@JsonClass(generateAdapter = true)
data class TranslationDataModel(
    @field:Json(name = "text") val text: String,
    @field:Json(name = "detectedLanguageCode") val detectedLanguageCode: String,
)