package ru.nikitae57.dictionary

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.nikitae57.dictionary.Screens.Main
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

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf(Replace(Main())))
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
}