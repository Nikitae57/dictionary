package ru.nikitae57.domain.translation.translate

data class TextToTranslateDomainModel(
    val text: String,
    val fromLanguageLabel: String,
    val toLanguageLabel: String
)