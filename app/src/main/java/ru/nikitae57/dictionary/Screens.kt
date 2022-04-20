package ru.nikitae57.dictionary

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment

object Screens {
    fun main() = FragmentScreen {
        SavedTranslationsFragment.newInstance()
    }

    fun addTranslation() = FragmentScreen {
        AddTranslationFragment.newInstance()
    }
}