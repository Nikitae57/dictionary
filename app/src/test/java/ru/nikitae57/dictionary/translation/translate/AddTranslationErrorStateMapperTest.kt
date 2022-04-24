package ru.nikitae57.dictionary.translation.translate

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res

private const val resErrorMessage = "errorMessage"

class AddTranslationErrorStateMapperTest {
    private val res = mockk<Res> {
        every { getString(R.string.add_translation_error_message) } returns resErrorMessage
    }
    private val mapper = AddTranslationErrorStateMapper(res)

    @Test
    fun `GIVEN message argument is null WHEN invoked THEN should return text from resources`() {
        val model = mapper()

        assertEquals(resErrorMessage, model.errorMessage)
    }

    @Test
    fun `GIVEN message argument is NOT null WHEN invoked THEN should return passed error message`() {
        val errorMessage = "errorMessage"
        val model = mapper(errorMessage)

        assertEquals(errorMessage, model.errorMessage)
    }
}