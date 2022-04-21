package ru.nikitae57.domain.translation.translate

data class WordToTranslateDomainModel(
    val text: String,
    val fromLanguageLabel: String,
    val toLanguageLabel: String
)