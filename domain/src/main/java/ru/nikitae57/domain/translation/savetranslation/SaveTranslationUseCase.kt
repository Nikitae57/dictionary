package ru.nikitae57.domain.translation.savetranslation

import ru.nikitae57.domain.translation.SavedTranslationsSource
import ru.nikitae57.domain.translation.models.WordDomainModel
import javax.inject.Inject

class SaveTranslationUseCase @Inject constructor(
    private val savedTranslationsSource: SavedTranslationsSource
) {
    operator fun invoke(wordDomainModel: WordDomainModel) = savedTranslationsSource.saveTranslation(wordDomainModel)
}