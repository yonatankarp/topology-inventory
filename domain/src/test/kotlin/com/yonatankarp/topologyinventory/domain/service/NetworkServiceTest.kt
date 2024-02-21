package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NetworkServiceTest {
    @Test
    fun `filterAndRetrieveNetworks returns filtered networks based on the predicate`() {
        // Given
        val networks =
            listOf(
                NetworkFixture.createNetwork("192.168.1.1", 24u),
                NetworkFixture.createNetwork("192.168.2.1", 24u),
                NetworkFixture.createNetwork("192.168.3.1", 24u),
            )
        val predicate: (Network) -> Boolean =
            { it.address.ipAddress == "192.168.2.1" }

        // When
        val filteredNetworks =
            NetworkService.filterAndRetrieveNetworks(networks, predicate)

        // Then
        assertEquals(1, filteredNetworks.size)
        assertEquals(
            NetworkFixture.createNetwork("192.168.2.1", 24u),
            filteredNetworks.first(),
        )
    }

    @Test
    fun `filterAndRetrieveNetworks returns an empty list if no networks match the predicate`() {
        // Given
        val networks =
            listOf(
                NetworkFixture.createNetwork("192.168.1.1", 24u),
                NetworkFixture.createNetwork("192.168.2.1", 24u),
                NetworkFixture.createNetwork("192.168.3.1", 24u),
            )
        val predicate: (Network) -> Boolean =
            { it.address.ipAddress == "192.168.4.1" }

        // When
        val filteredNetworks =
            NetworkService.filterAndRetrieveNetworks(networks, predicate)

        // Then
        assertTrue(filteredNetworks.isEmpty())
    }
}
