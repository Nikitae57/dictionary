package ru.nikitae57.dictionary

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.nikitae57.data.translation.di.DaggerTranslationDataComponent
import ru.nikitae57.data.translation.di.TranslationDataModule
import ru.nikitae57.dictionary.core.BackButtonListener
import ru.nikitae57.dictionary.translation.di.DaggerTranslationComponent
import ru.nikitae57.dictionary.translation.di.TranslationComponent
import javax.inject.Inject

class MainActivity : FragmentActivity(R.layout.activity_main) {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : AppNavigator(this, R.id.fragment_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    val translationComponent: TranslationComponent by lazy {
        val translationDataComponent = DaggerTranslationDataComponent.builder()
            .translationDataModule(TranslationDataModule())
            .dataComponent(getDataComponent())
            .build()

        DaggerTranslationComponent.builder()
            .appComponent(getAppComponent())
            .translationDataComponent(translationDataComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf(Replace(Screens.savedTranslations())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment != null && fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}