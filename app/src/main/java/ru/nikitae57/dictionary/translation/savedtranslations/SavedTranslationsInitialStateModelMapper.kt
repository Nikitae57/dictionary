package ru.nikitae57.dictionary.translation.savedtranslations

import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class SavedTranslationsInitialStateModelMapper @Inject constructor(
    private val resources: Res
) : () -> SavedTranslationsStateModel {
    override fun invoke() = SavedTranslationsStateModel.Initial(
        textInputHintText = resources.getString(R.string.find_word)
    )
}