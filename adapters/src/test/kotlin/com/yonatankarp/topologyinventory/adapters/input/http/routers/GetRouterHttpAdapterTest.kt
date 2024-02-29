package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetRouterHttpAdapterTest {
    private val getRouterPort = mockk<GetRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should retrieve router`() {
        // Given
        val id = Id.withoutId()
        val router = mockk<Router>()

        every { getRouterPort(id) } returns router

        val getRouterHttpAdapter = GetRouterHttpAdapter(getRouterPort)

        // When
        val result = getRouterHttpAdapter(id)

        // Then
        assertEquals(router, result)
    }
}
