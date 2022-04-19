package ru.nikitae57.dictionary

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nikitae57.dictionary.translation.mainscreen.MainScreenFragment

object Screens {
    fun Main() = FragmentScreen {
        MainScreenFragment.newInstance()
    }
}