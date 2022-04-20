package ru.nikitae57.domain.translation.savedtranslations

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Test
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel

private val dictionaryEntriesDomainModel = DictionaryEntriesDomainModel(entries = emptyList())

class GetSavedTranslationsUseCaseTest {
    private val savedTranslationsSource = mockk<SavedTranslationsSource> {
        every { getSavedTranslations() } returns Single.just(dictionaryEntriesDomainModel)
    }

    private val useCase = GetSavedTranslationsUseCase(savedTranslationsSource)

    @Test
    fun `GIVEN source succeed WHEN invoked THEN should return result from source`() {
        val testObserver = TestObserver<DictionaryEntriesDomainModel>()
        useCase().subscribe(testObserver)

        testObserver.assertValue(dictionaryEntriesDomainModel)
    }

    @Test
    fun `GIVEN source fails WHEN invoked THEN should return error`() {
        val error = Exception()
        every { savedTranslationsSource.getSavedTranslations() } returns Single.error(error)
        val testObserver = TestObserver<DictionaryEntriesDomainModel>()
        useCase().subscribe(testObserver)

        testObserver.assertError(error)
    }
}