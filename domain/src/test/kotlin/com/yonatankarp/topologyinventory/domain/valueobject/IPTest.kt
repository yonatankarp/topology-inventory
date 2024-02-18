package com.yonatankarp.topologyinventory.domain.valueobject

import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture.IPV4
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture.IPV6
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IPTest {
    @Test
    fun `IPv4 protocol for valid address`() {
        // Given
        val ipv4Address = IPV4

        // When
        val ip = IP.fromAddress(ipv4Address)

        // Then
        assertEquals(Protocol.IPv4, ip.protocol)
    }

    @Test
    fun `IPv6 protocol for valid address`() {
        // Given
        val ipv6Address = IPV6

        // When
        val ip = IP.fromAddress(ipv6Address)

        // Then
        assertEquals(Protocol.IPv6, ip.protocol)
    }
}
