package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class WordDomainModelMapper @Inject constructor() {
    operator fun invoke(wordStateModel: WordStateModel) = WordDomainModel(
        text = wordStateModel.text,
        languageLabel = wordStateModel.languageLabel,
    )
}