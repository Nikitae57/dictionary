package ru.nikitae57.dictionary.translation.translate

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.translation.models.WordStateModel

class DictionaryEntryDomainModelMapperTest {
    val mapper = DictionaryEntryDomainModelMapper()

    @Test
    fun `WHEN mapper is called THEN should return correct model`() {
        val from = WordStateModel(text = "text", languageLabel = "languageLabel")
        val to = WordStateModel(text = "text2", languageLabel = "languageLabel2")
        val model = mapper(from = from, to = to)

        assertEquals(model.words.size, 2)
        assertEquals(from.text, model.words[0].text)
        assertEquals(from.languageLabel, model.words[0].languageLabel)
        assertEquals(to.text, model.words[1].text)
        assertEquals(to.languageLabel, model.words[1].languageLabel)
    }
}