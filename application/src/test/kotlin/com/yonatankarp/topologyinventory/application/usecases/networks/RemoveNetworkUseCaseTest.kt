package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.output.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.RouterFixture
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class RemoveNetworkUseCaseTest {
    private val getRouterPort = mockk<GetRouterPort>(relaxed = true)
    private val addRouterPort = mockk<AddRouterPort>(relaxed = true)
    private val networkSwitch = mockk<Switch>(relaxed = true)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should remove network from switch and return the switch`() {
        // Given
        val network = NetworkFixture.network
        val edgeRouter = RouterFixture.edgeRouter
        edgeRouter.switches[networkSwitch.id] = networkSwitch
        every { getRouterPort(any()) } returns edgeRouter

        every { networkSwitch.removeNetworksFromSwitch(any()) } returns true
        val removeNetworkUseCase = RemoveNetworkUseCase(getRouterPort, addRouterPort)

        // When
        val result = removeNetworkUseCase(network.name, networkSwitch)

        // Then
        assertEquals(networkSwitch, result)
    }
}
