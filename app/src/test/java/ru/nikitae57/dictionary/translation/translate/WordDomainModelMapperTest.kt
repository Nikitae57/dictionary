package ru.nikitae57.dictionary.translation.translate

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.nikitae57.dictionary.translation.models.WordStateModel

class WordDomainModelMapperTest {
    private val mapper = WordDomainModelMapper()

    @Test
    fun `WHEN invoked THEN should return right model`() {
        val stateModel = WordStateModel(text = "text", languageLabel = "languageLabel")
        val model = mapper(stateModel)

        assertEquals(stateModel.text, model.text)
        assertEquals(stateModel.languageLabel, model.languageLabel)
    }
}