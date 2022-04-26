package ru.nikitae57.domain.core.token

import io.reactivex.Completable
import ru.nikitae57.domain.core.SchedulerProvider
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val tokenStore: TokenStore,
    private val tokenSource: TokenSource,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(): Completable {
        return tokenSource.getToken()
            .subscribeOn(schedulerProvider.io())
            .map {
                tokenStore.setToken(it)
            }.ignoreElement()
    }
}