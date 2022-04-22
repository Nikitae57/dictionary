package ru.nikitae57.dictionary

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nikitae57.dictionary.translation.savedtranslations.SavedTranslationsFragment
import ru.nikitae57.dictionary.translation.translate.AddTranslationFragment

object Screens {
    fun main() = ActivityScreen {
        Intent(it, MainActivity::class.java)
    }

    fun savedTranslations() = FragmentScreen {
        SavedTranslationsFragment.newInstance()
    }

    fun addTranslation() = FragmentScreen {
        AddTranslationFragment.newInstance()
    }
}