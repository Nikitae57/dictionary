package ru.nikitae57.dictionary.launcher

import android.os.Bundle
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.nikitae57.dictionary.R
import ru.nikitae57.dictionary.getAppComponent
import javax.inject.Inject
import javax.inject.Provider

class LauncherActivity : MvpAppCompatActivity(), LauncherView {

    @Inject
    lateinit var presenterProvider: Provider<LauncherPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}