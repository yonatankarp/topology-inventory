package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.RemoveRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class RemoveRouterHttpAdapterTest {
    private val getRouterPort = mockk<GetRouterPort>()
    private val removeRouterPort = mockk<RemoveRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should remove router`() {
        // Given
        val id = Id.withoutId()
        val router = mockk<Router>()

        every { removeRouterPort(id) } returns router

        val removeRouterHttpAdapter = RemoveRouterHttpAdapter(mockk(), removeRouterPort)

        // When
        val result = removeRouterHttpAdapter(id)

        // Then
        assertEquals(router, result)
    }

    @Test
    fun `fromCoreRouter should remove router from core router`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val removedRouter = mockk<Router>()

        every { getRouterPort(routerId) } returns router
        every { getRouterPort(coreRouterId) } returns coreRouter
        every { removeRouterPort.fromCoreRouter(router, coreRouter) } returns removedRouter

        val removeRouterHttpAdapter = RemoveRouterHttpAdapter(getRouterPort, removeRouterPort)

        // When
        val result = removeRouterHttpAdapter.fromCoreRouter(routerId, coreRouterId)

        // Then
        assertEquals(removedRouter, result)
    }

    @Test
    fun `fromCoreRouter should throw IllegalArgumentException when router id does not exist`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()

        every { getRouterPort(routerId) } returns null

        val removeRouterHttpAdapter = RemoveRouterHttpAdapter(getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            removeRouterHttpAdapter.fromCoreRouter(routerId, coreRouterId)
        }
    }

    @Test
    fun `fromCoreRouter should throw IllegalArgumentException when core router id does not exist`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()

        every { getRouterPort(routerId) } returns mockk()
        every { getRouterPort(coreRouterId) } returns null

        val removeRouterHttpAdapter = RemoveRouterHttpAdapter(getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            removeRouterHttpAdapter.fromCoreRouter(routerId, coreRouterId)
        }
    }
}
