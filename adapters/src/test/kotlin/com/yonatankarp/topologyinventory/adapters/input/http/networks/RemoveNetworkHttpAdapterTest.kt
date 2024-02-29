package com.yonatankarp.topologyinventory.adapters.input.http.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.RemoveNetworkPort
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

class RemoveNetworkHttpAdapterTest {
    private val getSwitchPort = mockk<GetSwitchPort>()
    private val removeNetworkPort = mockk<RemoveNetworkPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should remove network from switch`() {
        // Given
        val networkName = NetworkFixture.network.name
        val switchId = Id.withoutId()
        val networkSwitch = mockk<Switch>()
        val updatedSwitch = mockk<Switch>()

        every { getSwitchPort(switchId) } returns networkSwitch
        every { removeNetworkPort(networkName, networkSwitch) } returns updatedSwitch

        val removeNetworkHttpAdapter = RemoveNetworkHttpAdapter(getSwitchPort, removeNetworkPort)

        // When
        val result = removeNetworkHttpAdapter(networkName, switchId)

        // Then
        assertEquals(updatedSwitch, result)
    }

    @Test
    fun `invoke should throw IllegalArgumentException when switch does not exist`() {
        // Given
        val networkName = NetworkFixture.network.name
        val switchId = Id.withoutId()

        val getSwitchPort = mockk<GetSwitchPort>()

        every { getSwitchPort(switchId) } returns null

        val removeNetworkHttpAdapter = RemoveNetworkHttpAdapter(getSwitchPort, mockk())

        // Then
        assertThrows(IllegalArgumentException::class.java) {
            // When
            removeNetworkHttpAdapter(networkName, switchId)
        }
    }
}
