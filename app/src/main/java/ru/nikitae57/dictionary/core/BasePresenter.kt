package ru.nikitae57.dictionary.core

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<View : MvpView> : MvpPresenter<View>() {
    private val compositeDisposable = CompositeDisposable()

    protected fun addToDisposables(disposable: Disposable) = compositeDisposable.add(disposable)

    protected fun <T> prepareSingle(single: Single<T>) = single
        .subscribeOn(Schedulers.io())

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}