package ru.nikitae57.dictionary.translation.mainscreen

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

private val initialState = MainScreenStateModel.Initial(
    textInputHintText = "textInputHintText"
)

class MainScreenPresenterTest {
    private val view = mockk<MainScreenView>(relaxed = true)
    private val initialStateMapper = mockk<MainScreenInitialStateModelMapper>() {
        val mock = this
        every { mock.invoke() } returns initialState
    }

    @Test
    fun `WHEN creating presenter THEN it should be in initial state`() {
        MainScreenPresenter(initialStateMapper).apply { attachView(view) }

        verify { view.showInitialState(initialState) }
    }
}