package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.RemoveSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class RemoveSwitchHttpAdapterTest {
    private val removeSwitchPort = mockk<RemoveSwitchPort>()
    private val getRouterPort = mockk<GetRouterPort>()
    private val addRouterPort = mockk<AddRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should remove switch from edge router`() {
        // Given
        val switchId = Id.withoutId()
        val edgeRouterId = Id.withoutId()
        val edgeRouter = mockk<EdgeRouter>()
        val networkSwitch = mockk<Switch>()
        val router = mockk<EdgeRouter>()
        val persistedEdgeRouter = mockk<EdgeRouter>()

        every { getRouterPort(edgeRouterId) } returns edgeRouter
        every { edgeRouter.switches[switchId] } returns networkSwitch
        every { removeSwitchPort(networkSwitch, edgeRouter) } returns router
        every { addRouterPort.persist(router) } returns persistedEdgeRouter

        val removeSwitchHttpAdapter =
            RemoveSwitchHttpAdapter(
                removeSwitchPort,
                getRouterPort,
                addRouterPort,
            )

        // When
        val result = removeSwitchHttpAdapter(switchId, edgeRouterId)

        // Then
        assertEquals(persistedEdgeRouter, result)
    }

    @Test
    fun `invoke should throw IllegalArgumentException when edge router id does not exist`() {
        // Given
        val switchId = Id.withoutId()
        val edgeRouterId = Id.withoutId()

        every { getRouterPort(edgeRouterId) } returns null

        val removeSwitchHttpAdapter =
            RemoveSwitchHttpAdapter(mockk(), getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            removeSwitchHttpAdapter(switchId, edgeRouterId)
        }
    }

    @Test
    fun `invoke should throw IllegalArgumentException when switch id does not exist`() {
        // Given
        val switchId = Id.withoutId()
        val edgeRouterId = Id.withoutId()
        val edgeRouter = mockk<EdgeRouter>()

        every { getRouterPort(edgeRouterId) } returns edgeRouter
        every { edgeRouter.switches[switchId] } returns null

        val removeSwitchHttpAdapter =
            RemoveSwitchHttpAdapter(mockk(), getRouterPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            removeSwitchHttpAdapter(switchId, edgeRouterId)
        }
    }
}
