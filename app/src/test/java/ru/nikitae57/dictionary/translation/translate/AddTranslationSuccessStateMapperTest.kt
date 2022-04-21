package ru.nikitae57.dictionary.translation.translate

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.WordStateModel
import ru.nikitae57.domain.core.Res

private const val add_translation_button_text = "add_translation_button_text"
private const val word_input_hint = "word_input_hint"
private const val ru_language_label = "ru_language_label"
private const val en_language_label = "en_language_label"
private const val fr_language_label = "fr_language_label"

class AddTranslationSuccessStateMapperTest {
    private val res = mockk<Res> {
        every { getString(R.string.add_translation_button_text) } returns add_translation_button_text
        every { getString(R.string.word_input_hint) } returns word_input_hint
        every { getString(R.string.ru_language_label) } returns ru_language_label
        every { getString(R.string.en_language_label) } returns en_language_label
        every { getString(R.string.fr_language_label) } returns fr_language_label
    }
    val mapper = AddTranslationSuccessStateMapper(res)

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val savedTranslations = emptyList<WordStateModel>()
        val model = mapper(savedTranslations)

        assertEquals(savedTranslations, model.savedTranslations)
        assertEquals(add_translation_button_text, model.addButtonText)
        assertEquals(word_input_hint, model.wordInputHint)
        assertEquals(3, model.fromLanguageLabels.size)
        assertEquals(ru_language_label, model.fromLanguageLabels[0].label)
        assertEquals(en_language_label, model.fromLanguageLabels[1].label)
        assertEquals(fr_language_label, model.fromLanguageLabels[2].label)
    }
}