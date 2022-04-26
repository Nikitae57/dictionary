package ru.nikitae57.dictionary.translation.translate

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res

private const val add_translation_button_text = "add_translation_button_text"
private const val word_input_hint = "word_input_hint"
private const val ru_language_label = "ru_language_label"
private const val en_language_label = "en_language_label"
private const val fr_language_label = "fr_language_label"
private const val to_languages_spinner_label = "to_languages_spinner_label"
private const val from_languages_spinner_label = "from_languages_spinner_label"

class AddTranslationSuccessStateMapperTest {
    private val res = mockk<Res> {
        every { getString(R.string.add_translation_button_text) } returns add_translation_button_text
        every { getString(R.string.word_input_hint) } returns word_input_hint
        every { getString(R.string.ru_language_label) } returns ru_language_label
        every { getString(R.string.en_language_label) } returns en_language_label
        every { getString(R.string.fr_language_label) } returns fr_language_label
        every { getString(R.string.to_languages_spinner_label) } returns to_languages_spinner_label
        every { getString(R.string.from_languages_spinner_label) } returns from_languages_spinner_label
    }
    val mapper = AddTranslationSuccessStateMapper(res)

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val model = mapper()

        assertEquals(add_translation_button_text, model.addButtonText)
        assertEquals(word_input_hint, model.wordInputHint)
        assertEquals(3, model.fromLanguageLabels.size)
        assertEquals(ru_language_label, model.fromLanguageLabels[0])
        assertEquals(en_language_label, model.fromLanguageLabels[1])
        assertEquals(fr_language_label, model.fromLanguageLabels[2])
        assertEquals(en_language_label, model.toLanguageLabels[0])
        assertEquals(ru_language_label, model.toLanguageLabels[1])
        assertEquals(fr_language_label, model.toLanguageLabels[2])
        assertEquals(R.drawable.ic_compare_arrows_24, model.swapLanguagesIconId)
        assertEquals(to_languages_spinner_label, model.toLanguagesSpinnerLabel)
        assertEquals(from_languages_spinner_label, model.fromLanguagesSpinnerLabel)
    }
}