package ru.nikitae57.domain.translation.savedtranslations

import ru.nikitae57.domain.translation.SavedTranslationsSource
import javax.inject.Inject

class GetSavedTranslationsUseCase @Inject constructor(
    private val savedTranslationsSource: SavedTranslationsSource
) {
    operator fun invoke() = savedTranslationsSource.getSavedTranslations()
}