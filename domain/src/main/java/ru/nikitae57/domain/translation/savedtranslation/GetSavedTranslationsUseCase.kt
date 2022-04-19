package ru.nikitae57.domain.translation.savedtranslation

import javax.inject.Inject

class GetSavedTranslationsUseCase @Inject constructor(
    private val savedTranslationsSource: SavedTranslationsSource
) {
    operator fun invoke() = savedTranslationsSource.getSavedTranslations()
}