package ru.nikitae57.dictionary.translation.mainscreen

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res

private const val findWord = "Find word"

class MainScreenInitialStateModelMapperTest {
    private val resources = mockk<Res> {
        every { getString(R.string.find_word) } returns findWord
    }

    private val mapper = MainScreenInitialStateModelMapper(resources = resources)

    @Test
    fun whenMapperInvokedShouldReturnCorrectModel() {
        val result = mapper()

        assertEquals(result.textInputHintText, findWord)
    }
}