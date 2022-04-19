package ru.nikitae57.dictionary.translation.mainscreen

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onIdle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.runner.RunWith
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.translation.models.DictionaryEntriesStateModel
import ru.nikitae57.dictionary.translation.models.DictionaryEntryStateModel
import ru.nikitae57.dictionary.translation.models.LanguagesStateModel
import ru.nikitae57.dictionary.translation.models.WordStateModel
import javax.inject.Provider

@RunWith(AndroidJUnit4::class)
class MainScreenFragmentTest {

    private val presenter = mockk<MainScreenPresenter>(relaxed = true)
    private val mPresenterProvider = mockk<Provider<MainScreenPresenter>> {
        every { get() } returns presenter
    }

    @Test
    fun whenShowingInitialStateThenShouldShowNothing() {
        val stateModel = MainScreenStateModel.Initial(textInputHintText = "textInputHintText")

        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }
        onIdle {
            scenario.onFragment { it.showInitialState(stateModel) }
        }

        onView(withId(R.id.fab)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchInput)).check(matches(not(isDisplayed())))
        onView(withId(R.id.errorNotification)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenShowingLoadingStateThenShouldShowOnlyProgressBar() {
        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }
        onIdle {
            scenario.onFragment { it.showLoadingState() }
        }

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchInput)).check(matches(not(isDisplayed())))
        onView(withId(R.id.errorNotification)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenShowingSuccessStateThenShouldShowRightContent() {
        val stateModel = MainScreenStateModel.Success(
            dictionaryEntryStateModels = DictionaryEntriesStateModel(
                listOf(
                    DictionaryEntryStateModel(
                        words = listOf(
                            WordStateModel(text = "text1", language = LanguagesStateModel.RU, languageLabel = "label1"),
                            WordStateModel(text = "text2", language = LanguagesStateModel.RU, languageLabel = "label2"),
                            WordStateModel(text = "text3", language = LanguagesStateModel.RU, languageLabel = "label3")
                        )
                    ),
                    DictionaryEntryStateModel(
                        words = listOf(
                            WordStateModel(text = "text4", language = LanguagesStateModel.RU, languageLabel = "label4"),
                        )
                    )
                )
            )
        )
        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }
        onIdle {
            scenario.onFragment { it.showSuccessState(stateModel) }
        }

        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchInput)).check(matches(isDisplayed()))
        onView(withId(R.id.errorNotification)).check(matches(not(isDisplayed())))
        stateModel.dictionaryEntryStateModels.entries.forEach { dictionaryEntryStateModel ->
            dictionaryEntryStateModel.words.forEach { wordStateModel ->
                onView(withText(wordStateModel.text)).check(matches(isDisplayed()))
                onView(withText(wordStateModel.languageLabel)).check(matches(isDisplayed()))
            }
        }
    }

    @Test
    fun whenShowingErrorStateThenShouldShowErrorNotification() {
        val stateModel = MainScreenStateModel.Error(
            tryAgainButtonText = "tryAgainButtonText",
            errorMessage = "errorMessage",
            tryAgainAction = {}
        )
        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }
        onIdle {
            scenario.onFragment { it.showErrorState(stateModel) }
        }

        onView(withText(stateModel.errorMessage)).check(matches(isDisplayed()))
        onView(withText(stateModel.tryAgainButtonText.toString())).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.fab)).check(matches(not(isDisplayed())))
        onView(withId(R.id.translationsList)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchInput)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenErrorStateWhenClickingOnTryAgainButtonThenShouldInvokeTryAgainAction() {
        val tryAgainAction = mockk<() -> Unit>(relaxed = true)
        val stateModel = MainScreenStateModel.Error(
            tryAgainButtonText = "tryAgainButtonText",
            errorMessage = "errorMessage",
            tryAgainAction = tryAgainAction
        )
        val scenario = launchFragmentInContainer(themeResId = R.style.Theme_Dictionary) {
            MainScreenFragment().apply {
                presenterProvider = mPresenterProvider
            }
        }
        onIdle {
            scenario.onFragment { it.showErrorState(stateModel) }
        }

        onView(withId(R.id.tryAgainButton)).perform(click())

        onIdle {
            verify { tryAgainAction() }
        }
    }
}