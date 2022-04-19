package ru.nikitae57.dictionary.translation.models

data class WordStateModel(
    val text: String,
    val language: LanguagesStateModel,
    val languageLabel: String
)
