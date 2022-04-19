package ru.nikitae57.dictionary.translation.mainscreen

import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class MainScreenInitialStateModelMapper @Inject constructor(
    private val resources: Res
) : () -> MainScreenStateModel {
    override fun invoke() = MainScreenStateModel.Initial(
        textInputHintText = resources.getString(R.string.find_word)
    )
}