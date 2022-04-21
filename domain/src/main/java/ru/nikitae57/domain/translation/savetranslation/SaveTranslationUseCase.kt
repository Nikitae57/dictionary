package ru.nikitae57.domain.translation.savetranslation

import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import javax.inject.Inject

class SaveTranslationUseCase @Inject constructor(
    private val savedTranslationsSource: SavedTranslationsSource
) {
    operator fun invoke(dictionaryEntryDomainModel: DictionaryEntryDomainModel) = savedTranslationsSource.saveTranslation(dictionaryEntryDomainModel)
}