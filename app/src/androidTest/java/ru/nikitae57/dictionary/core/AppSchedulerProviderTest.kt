package ru.nikitae57.dictionary.core

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class AppSchedulerProviderTest {
    private val schedulerProvider = AppSchedulerProvider()

    @Test
    fun whenCallingIoThenShouldReturnIo() {
        val scheduler = schedulerProvider.io()

        assertEquals(scheduler, Schedulers.io())
    }

    @Test
    fun whenCallingUiThenShouldReturnAndroidMainThread() {
        val scheduler = schedulerProvider.ui()

        assertEquals(scheduler, AndroidSchedulers.mainThread())
    }
}