package ru.nikitae57.dictionary.translation.savedtranslations

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res

private const val tryAgainButtonText = "tryAgainButtonText"
private const val errorMessage = "errorMessage"
private val tryAgainAction = {}

class MainScreenErrorStateModelMapperTest {
    private val resources = mockk<Res>{
        every { getString(R.string.try_again_button_text) } returns tryAgainButtonText
        every { getString(R.string.error_message) } returns errorMessage
    }

    private val mapper = SavedTranslationsErrorStateModelMapper(resources = resources)

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val model = mapper(tryAgainAction = tryAgainAction)

        assert(model.errorMessage == errorMessage)
        assert(model.tryAgainButtonText == tryAgainButtonText)
        assert(model.tryAgainAction == tryAgainAction)
    }
}