package ru.nikitae57.dictionary

import io.reactivex.schedulers.Schedulers
import ru.nikitae57.domain.core.SchedulerProvider

class TestSchedulerProvider : SchedulerProvider {
    override fun io() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()
}