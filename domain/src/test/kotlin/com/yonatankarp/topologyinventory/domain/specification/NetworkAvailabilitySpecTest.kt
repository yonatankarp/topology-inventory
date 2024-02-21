package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NetworkAvailabilitySpecTest {
    @Test
    fun `isSatisfiedBy returns true when network is not present in switch networks`() {
        // Given
        val network = NetworkFixture.network
        val switch = SwitchFixture.emptySwitch
        val networkAvailabilitySpec = NetworkAvailabilitySpec(network)

        // When
        val result = networkAvailabilitySpec.isSatisfiedBy(switch)

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when network is already present in switch networks`() {
        // Given
        val network = NetworkFixture.network
        val switch = SwitchFixture.emptySwitch
        switch.addNetworkToSwitch(network)
        val networkAvailabilitySpec = NetworkAvailabilitySpec(network)

        // When
        val result = networkAvailabilitySpec.isSatisfiedBy(switch)

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when network already exists in switch networks`() {
        // Given
        val network = NetworkFixture.network
        val switch = SwitchFixture.emptySwitch
        switch.addNetworkToSwitch(network)
        val networkAvailabilitySpec = NetworkAvailabilitySpec(network)

        // When, Then
        assertThrows(GenericSpecificationException::class.java) {
            networkAvailabilitySpec.check(switch)
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when network does not exist in switch networks`() {
        // Given
        val network = NetworkFixture.network
        val switch = SwitchFixture.emptySwitch
        val networkAvailabilitySpec = NetworkAvailabilitySpec(network)

        // Then
        assertDoesNotThrow {
            // When
            networkAvailabilitySpec.check(switch)
        }
    }
}
