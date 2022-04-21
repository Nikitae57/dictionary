package ru.nikitae57.dictionary.translation.translate

data class WordToTranslateStateModel(
    val text: String,
    val fromLanguageLabel: String,
    val toLanguageLabel: String
)