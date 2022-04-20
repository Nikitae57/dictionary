package ru.nikitae57.dictionary.translation.savedtranslations

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import ru.nikitae57.dictionary.R
import ru.nikitae57.domain.core.Res
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel

private const val ruLanguageLabel = "ru_language_label"
private const val enLanguageLabel = "en_language_label"
private const val frLanguageLabel = "fr_language_label"

class SavedTranslationsSuccessStateMapperTest {
    private val resources = mockk<Res> {
        every { getString(R.string.ru_language_label) } returns ruLanguageLabel
        every { getString(R.string.en_language_label) } returns enLanguageLabel
        every { getString(R.string.fr_language_label) } returns frLanguageLabel
    }

    private val mapper = SavedTranslationsSuccessStateMapper(resources = resources)

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val dictionaryEntriesDomainModel = DictionaryEntriesDomainModel(
            entries = listOf(
                DictionaryEntryDomainModel(
                    words = listOf(
                        WordDomainModel(text = "text", languageLabel = "ru"),
                        WordDomainModel(text = "text2", languageLabel = "en")
                    )
                ),
                DictionaryEntryDomainModel(
                    words = listOf(
                        WordDomainModel(text = "text3", languageLabel = "fr"),
                    )
                )
            )
        )
        val model = mapper(dictionaryEntriesDomainModel)

        model.dictionaryEntryStateModels.entries.forEachIndexed { i, dictionaryEntryStateModel ->
            val dictionaryEntryDomainModel = dictionaryEntriesDomainModel.entries[i]
            dictionaryEntryStateModel.words.forEachIndexed { j, wordStateModel ->
                assert(dictionaryEntryDomainModel.words[j].text == wordStateModel.text)
                assert(dictionaryEntryDomainModel.words[j].languageLabel == wordStateModel.languageLabel)
            }
        }
    }
}