package ru.nikitae57.dictionary.translation.mainscreen

import com.github.terrakok.cicerone.Router
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test
import ru.nikitae57.dictionary.TestSchedulerProvider
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.translation.models.DictionaryEntriesDomainModel
import ru.nikitae57.domain.translation.savedtranslation.GetSavedTranslationsUseCase

private val initialState = MainScreenStateModel.Initial(
    textInputHintText = "textInputHintText"
)

private val dictionaryEntriesDomainModel = DictionaryEntriesDomainModel(entries = emptyList())

private val successState = MainScreenStateModel.Success(
    dictionaryEntryStateModels = DictionaryEntriesStateModel.createEmpty()
)

private val errorState = MainScreenStateModel.Error(
    tryAgainAction = {},
    tryAgainButtonText = "tryAgainButtonText",
    errorMessage = "errorMessage"
)

class MainScreenPresenterTest {
    private val view = mockk<MainScreenView>(relaxed = true)
    private val router = mockk<Router>(relaxed = true)
    private val initialStateMapper = mockk<MainScreenInitialStateModelMapper>() {
        val mock = this
        every { mock.invoke() } returns initialState
    }
    private val successStateMapper = mockk<MainScreenSuccessStateMapper> {
        val mock = this
        every { mock.invoke(dictionaryEntriesDomainModel) } returns successState
    }
    private val errorStateModelMapper = mockk<MainScreenErrorStateModelMapper> {
        val mock = this
        every { mock.invoke(any()) } returns errorState
    }
    private val getSavedTranslationsUseCase = mockk<GetSavedTranslationsUseCase> {
        val mock = this
        every { mock.invoke() } returns Single.just(dictionaryEntriesDomainModel)
    }

    @Test
    fun `WHEN creating presenter THEN it should be in initial state`() {
        getPresenter()

        verify { view.showInitialState(initialState) }
    }

    @Test
    fun `GIVEN use case succeed WHEN loadSavedTranslations is called THEN should show success state`() {
        getPresenter().loadSavedTranslations()

        verify { view.showLoadingState() }
        verify { view.showSuccessState(successState) }
        verify(exactly = 0) { view.showErrorState(any()) }
    }

    @Test
    fun `GIVEN use case fails WHEN loadSavedTranslations is called THEN should show error state`() {
        every { getSavedTranslationsUseCase.invoke() } returns Single.error(Exception())
        getPresenter().loadSavedTranslations()

        verify { view.showLoadingState() }
        verify { view.showErrorState(errorState) }
        verify(exactly = 0) { view.showSuccessState(any()) }
    }

    private fun getPresenter() = MainScreenPresenter(
        router = router,
        initialStateMapper = initialStateMapper,
        successStateMapper = successStateMapper,
        errorStateModelMapper = errorStateModelMapper,
        getSavedTranslationsUseCase = getSavedTranslationsUseCase,
        schedulerProvider = TestSchedulerProvider()
    ).apply { attachView(view) }
}