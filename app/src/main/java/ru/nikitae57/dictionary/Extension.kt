package ru.nikitae57.dictionary

import android.app.Activity
import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import ru.nikitae57.dictionary.di.AppComponent

fun Fragment.getAppComponent(): AppComponent = (requireContext().applicationContext as App).appComponent

fun Activity.getAppComponent(): AppComponent = (applicationContext as App).appComponent

val Fragment.isRunningTest : Boolean by lazy {
    try {
        Class.forName("androidx.test.espresso.Espresso")
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}