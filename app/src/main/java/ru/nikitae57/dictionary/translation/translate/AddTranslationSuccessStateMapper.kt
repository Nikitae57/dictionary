package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class AddTranslationSuccessStateMapper @Inject constructor(
    private val res: Res
) {
    operator fun invoke(savedTranslations: List<WordStateModel>) = AddTranslationStateModel.Success(
        addButtonText = res.getString(R.string.add_translation_button_text),
        wordInputHint = res.getString(R.string.word_input_hint),
        languageLabels = listOf(
            res.getString(R.string.ru_language_label),
            res.getString(R.string.en_language_label),
            res.getString(R.string.fr_language_label)
        ),
        savedTranslations = savedTranslations
    )
}