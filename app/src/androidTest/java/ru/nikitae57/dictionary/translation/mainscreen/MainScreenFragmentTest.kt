package ru.nikitae57.dictionary.translation.mainscreen

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onIdle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.textInputLayoutHasHint
import javax.inject.Provider

@RunWith(AndroidJUnit4::class)
class MainScreenFragmentTest {

    private val presenter = mockk<MainScreenPresenter>(relaxed = true)
    private val mPresenterProvider = mockk<Provider<MainScreenPresenter>> {
        every { get() } returns presenter
    }

    @Test
    fun givenInitialStateWhenObservingViewModelThenShouldRenderProperFragment() {
        val stateModel = MainScreenStateModel.Initial(textInputHintText = "textInputHintText")

        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }

        onIdle {
            scenario.onFragment {
                it.showInitialState(stateModel)
            }
        }


        onView(withId(R.id.searchInput))
            .check(matches(textInputLayoutHasHint(stateModel.textInputHintText.toString())))
    }
}