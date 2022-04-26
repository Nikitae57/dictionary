package ru.nikitae57.dictionary.translation.savedtranslations

import org.junit.Test
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.models.DictionaryEntryDomainModel
import ru.nikitae57.domain.translation.models.WordDomainModel

class SavedTranslationsSuccessStateMapperTest {

    private val mapper = SavedTranslationsSuccessStateMapper()

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

        model.dictionaryEntriesStateModel.entries.forEachIndexed { i, dictionaryEntryStateModel ->
            val dictionaryEntryDomainModel = dictionaryEntriesDomainModel.entries[i]
            dictionaryEntryStateModel.words.forEachIndexed { j, wordStateModel ->
                assert(dictionaryEntryDomainModel.words[j].text == wordStateModel.text)
                assert(dictionaryEntryDomainModel.words[j].languageLabel == wordStateModel.languageLabel)
            }
        }
    }
}