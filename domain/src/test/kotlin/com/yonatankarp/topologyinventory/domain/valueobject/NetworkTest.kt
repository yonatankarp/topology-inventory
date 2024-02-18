package com.yonatankarp.topologyinventory.domain.valueobject

import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture.IPV4
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.Test

class NetworkTest {
    @Test
    fun `network properties are correctly initialized`() {
        // Given
        val ipAddress = IP.fromAddress(IPV4)
        val networkName = NetworkFixture.networkName
        val cidr = NetworkFixture.cidr

        // When
        val network = Network(ipAddress, networkName, cidr)

        // Then
        assert(network.address == ipAddress)
        assert(network.name == networkName)
        assert(network.cidr == cidr)
    }

    @Test
    fun `invalid CIDR value throws IllegalArgumentException`() {
        // Given
        val ipAddress = IP.fromAddress(IPV4)
        val networkName = NetworkFixture.networkName
        val invalidCidr = CIDR(40u)

        // When, Then
        assertThrows(IllegalArgumentException::class.java) {
            Network(ipAddress, networkName, invalidCidr)
        }
    }
}
