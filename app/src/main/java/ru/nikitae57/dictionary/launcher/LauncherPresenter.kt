package ru.nikitae57.dictionary.launcher

import com.github.terrakok.cicerone.Router
import ru.nikitae57.dictionary.Screens
import ru.nikitae57.dictionary.core.BasePresenter
import ru.nikitae57.domain.core.SchedulerProvider
import ru.nikitae57.domain.core.token.RefreshTokenUseCase
import javax.inject.Inject

class LauncherPresenter @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val router: Router
) : BasePresenter<LauncherView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refreshTokenUseCase.invoke()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                router.newRootScreen(Screens.main())
            }
            .also { addToDisposables(it) }
    }
}