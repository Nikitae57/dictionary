package ru.nikitae57.dictionary

import android.app.Activity
import androidx.fragment.app.Fragment
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment

fun Fragment.getAppComponent() = (requireContext().applicationContext as App).appComponent

fun Activity.getAppComponent() = (applicationContext as App).appComponent

fun Activity.getDataComponent() = (applicationContext as App).dataComponent

fun SavedTranslationsFragment.getTranslationsComponent() = (requireActivity() as MainActivity).translationComponent

fun AddTranslationFragment.getTranslationsComponent() = (requireActivity() as MainActivity).translationComponent

val Fragment.isRunningTest: Boolean by lazy {
    try {
        Class.forName("androidx.test.espresso.Espresso")
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}