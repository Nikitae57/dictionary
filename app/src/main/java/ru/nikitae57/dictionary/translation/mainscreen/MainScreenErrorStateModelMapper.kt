package ru.nikitae57.dictionary.translation.mainscreen

import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class MainScreenErrorStateModelMapper @Inject constructor(
    private val resources: Res
) {
    operator fun invoke() = MainScreenStateModel.Error(
        tryAgainButtonText = resources.getString(R.string.try_again_button_text),
        errorMessage = resources.getString(R.string.error_message)
    )
}