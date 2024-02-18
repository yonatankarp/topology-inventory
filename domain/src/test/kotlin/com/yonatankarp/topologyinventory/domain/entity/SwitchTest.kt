package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture.createSwitch
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture.createLocation
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture.createNetwork
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SwitchTest {
    @Test
    fun `add network to switch`() {
        // Given
        val location = createLocation("US")
        val newNetwork = createNetwork("30.0.0.1", 8u)
        val networkSwitch = createSwitch("30.0.0.0", 8u, location)

        // When
        val result = networkSwitch.addNetworkToSwitch(newNetwork)

        // Then
        assertTrue(result)
    }

    @Test
    fun `add network to switch - fails because same network address`() {
        // Given
        val location = createLocation("US")
        val newNetwork = createNetwork("30.0.0.0", 8u)
        val networkSwitch = createSwitch("30.0.0.0", 8u, location)

        // Then
        assertThrows(GenericSpecificationException::class.java) {
            // When
            networkSwitch.addNetworkToSwitch(newNetwork)
        }
    }

    @Test
    fun `remove network`() {
        // Given
        val location = createLocation("US")
        val network = createNetwork("30.0.0.0", 8u)
        val networkSwitch = createSwitch("30.0.0.0", 8u, location)

        // When
        assertEquals(1, networkSwitch.switchNetworks.size)
        assertTrue(networkSwitch.removeNetworkFromSwitch(network))

        // Then
        assertEquals(0, networkSwitch.switchNetworks.size)
    }
}
