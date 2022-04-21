package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class DictionaryEntryDomainModelMapper @Inject constructor() {
    operator fun invoke(from: WordStateModel, to: WordStateModel) = DictionaryEntryDomainModel(
        words = listOf(
            WordDomainModel(text = from.text.toString(), languageLabel = from.languageLabel.toString()),
            WordDomainModel(text = to.text.toString(), languageLabel = to.languageLabel.toString())
        )
    )
}