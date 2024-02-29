package com.yonatankarp.topologyinventory.adapters.input.http.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.AddNetworkPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AddNetworkHttpAdapterTest {
    private val getSwitchPort = mockk<GetSwitchPort>()
    private val addNetworkPort = mockk<AddNetworkPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should add network to switch`() {
        // Given
        val network = NetworkFixture.network
        val switchId = Id.withoutId()
        val networkSwitch = mockk<Switch>()
        val addedSwitch = mockk<Switch>()

        every { getSwitchPort(switchId) } returns networkSwitch
        every { addNetworkPort(network, networkSwitch) } returns addedSwitch

        val addNetworkHttpAdapter = AddNetworkHttpAdapter(getSwitchPort, addNetworkPort)

        // When
        val result = addNetworkHttpAdapter(network, switchId)

        // Then
        assertEquals(addedSwitch, result)
    }

    @Test
    fun `invoke should throw IllegalArgumentException when switch does not exist`() {
        // Given
        val network = NetworkFixture.network
        val switchId = Id.withoutId()

        val getSwitchPort = mockk<GetSwitchPort>()

        every { getSwitchPort(switchId) } returns null

        val addNetworkHttpAdapter = AddNetworkHttpAdapter(getSwitchPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            addNetworkHttpAdapter(network, switchId)
        }
    }
}
