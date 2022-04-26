package ru.nikitae57.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.nikitae57.domain.core.SchedulerProvider
import javax.inject.Inject

class AppSchedulerProvider @Inject constructor() : SchedulerProvider {
    override fun io() = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}