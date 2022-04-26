package ru.nikitae57.domain.translation.translate

data class TranslationDomainModel(
    val translation: String,
    val originalText: String,
    val fromLanguageLabel: String,
    val toLanguageLabel: String,
)
