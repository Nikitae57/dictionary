package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class AddTranslationErrorStateMapper @Inject constructor(
    private val res: Res
) {
    operator fun invoke(errorMessage: String? = null) = AddTranslationStateModel.Error(
        errorMessage = errorMessage ?: res.getString(R.string.add_translation_error_message)
    )
}