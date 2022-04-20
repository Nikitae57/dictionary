package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.LanguageStateModel
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class AddTranslationInitialStateMapper @Inject constructor(
    private val res: Res
) {
    operator fun invoke() = AddTranslationStateModel.Initial(
        addButtonText = res.getString(R.string.add_translation_button_text),
        wordInputHint = res.getString(R.string.word_input_hint),
        languageLabels = listOf(
            LanguageStateModel(label = res.getString(R.string.ru_language_label)),
            LanguageStateModel(label = res.getString(R.string.en_language_label)),
            LanguageStateModel(label = res.getString(R.string.fr_language_label))
        )
    )
}