package ru.nikitae57.data.core.token

import org.junit.Assert.assertEquals
import org.junit.Test

class TokenDataProviderTest {
    @Test
    fun `GIVEN token provider in initial state WHEN getting token THEN should return initial value`() {
        val tokenProvider = TokenDataProvider()

        assertEquals("", tokenProvider.getToken())
    }

    @Test
    fun `GIVEN token is set WHEN getting token THEN should return set value`() {
        val tokenProvider = TokenDataProvider()
        val token = "token"

        tokenProvider.setToken(token)

        assertEquals(token, tokenProvider.getToken())
    }
}