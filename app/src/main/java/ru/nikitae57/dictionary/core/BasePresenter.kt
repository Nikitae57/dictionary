package ru.nikitae57.dictionary.core

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<View : MvpView> : MvpPresenter<View>() {
    private val compositeDisposable = CompositeDisposable()

    protected fun addToDisposables(disposable: Disposable) = compositeDisposable.add(disposable)

    @CallSuper
    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}