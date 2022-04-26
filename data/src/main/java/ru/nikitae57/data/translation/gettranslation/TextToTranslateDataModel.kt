package ru.nikitae57.data.translation.gettranslation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextToTranslateDataModel(
    @field:Json(name = "texts") val texts: List<String>,
    @field:Json(name = "folderId") val folderId: String,
    @field:Json(name = "targetLanguageCode") val targetLanguageCode: String
)