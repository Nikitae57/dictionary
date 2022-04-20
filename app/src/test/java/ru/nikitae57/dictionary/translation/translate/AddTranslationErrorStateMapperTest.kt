package ru.nikitae57.dictionary.translation.translate

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res

private const val errorMessage = "errorMessage"

class AddTranslationErrorStateMapperTest {
    private val res = mockk<Res> {
        every { getString(R.string.add_translation_error_message) } returns errorMessage
    }
    private val mapper = AddTranslationErrorStateMapper(res)

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val model = mapper()

        assertEquals(errorMessage, model.errorMessage)
    }
}