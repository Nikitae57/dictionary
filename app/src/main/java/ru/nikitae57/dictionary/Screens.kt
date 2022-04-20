package ru.nikitae57.dictionary

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment

object Screens {
    fun Main() = FragmentScreen {
        SavedTranslationsFragment.newInstance()
    }
}