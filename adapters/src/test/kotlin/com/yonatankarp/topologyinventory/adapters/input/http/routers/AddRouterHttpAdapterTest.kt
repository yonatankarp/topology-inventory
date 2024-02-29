package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
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

class AddRouterHttpAdapterTest {
    private val getRouterPort = mockk<GetRouterPort>()
    private val addRouterPort = mockk<AddRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should add router to core router`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val addedRouter = mockk<CoreRouter>()

        every { getRouterPort(routerId) } returns router
        every { getRouterPort(coreRouterId) } returns coreRouter
        every { addRouterPort(router, coreRouter) } returns addedRouter

        val addRouterHttpAdapter =
            AddRouterHttpAdapter(getRouterPort, addRouterPort)

        // When
        val result = addRouterHttpAdapter(routerId, coreRouterId)

        // Then
        assertEquals(addedRouter, result)
    }

    @Test
    fun `invoke should throw IllegalArgumentException when router id does not exist`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()

        every { getRouterPort(routerId) } returns null
        every { getRouterPort(coreRouterId) } returns mockk()

        val addRouterHttpAdapter = AddRouterHttpAdapter(getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            addRouterHttpAdapter(routerId, coreRouterId)
        }
    }

    @Test
    fun `invoke should throw IllegalArgumentException when core router id does not exist`() {
        // Given
        val routerId = Id.withoutId()
        val coreRouterId = Id.withoutId()

        every { getRouterPort(routerId) } returns mockk()
        every { getRouterPort(coreRouterId) } returns null

        val addRouterHttpAdapter = AddRouterHttpAdapter(getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            addRouterHttpAdapter(routerId, coreRouterId)
        }
    }
}
