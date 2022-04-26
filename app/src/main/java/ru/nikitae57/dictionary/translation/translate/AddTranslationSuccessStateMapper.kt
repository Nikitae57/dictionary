package ru.nikitae57.dictionary.translation.translate

import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import javax.inject.Inject

class AddTranslationSuccessStateMapper @Inject constructor(
    private val res: Res
) {
    operator fun invoke(): AddTranslationStateModel.Success {
        val toLanguages = listOf(
            res.getString(R.string.en_language_label),
            res.getString(R.string.ru_language_label),
            res.getString(R.string.fr_language_label)
        )
        val fromLanguages = listOf(
            res.getString(R.string.ru_language_label),
            res.getString(R.string.en_language_label),
            res.getString(R.string.fr_language_label)
        )

        return AddTranslationStateModel.Success(
            addButtonText = res.getString(R.string.add_translation_button_text),
            wordInputHint = res.getString(R.string.word_input_hint),
            fromLanguageLabels = fromLanguages,
            toLanguageLabels = toLanguages,
            toLanguagesSpinnerLabel = res.getString(R.string.to_languages_spinner_label),
            fromLanguagesSpinnerLabel = res.getString(R.string.from_languages_spinner_label),
            swapLanguagesIconId = R.drawable.ic_compare_arrows_24
        )
    }
}