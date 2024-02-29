package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.NetworkData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.NetworkMapper.fromData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.NetworkMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.NetworkMapper.toDomain
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class NetworkMapperTest {
    @Test
    fun `toDomain should map NetworkData to Network`() {
        // Given
        val networkData =
            NetworkData(
                ip = IPData(IPFixture.IPV4),
                name = NetworkFixture.networkName.value,
                cidr = NetworkFixture.cidr.value.toInt(),
            )

        // When
        val result = networkData.toDomain()

        // Then
        assertEquals(IPFixture.ipv4, result.address)
        assertEquals(NetworkFixture.networkName, result.name)
        assertEquals(NetworkFixture.cidr, result.cidr)
    }

    @Test
    fun `toData should map Network to NetworkData`() {
        // Given
        val network =
            Network(
                address = IPFixture.ipv4,
                name = NetworkFixture.networkName,
                cidr = NetworkFixture.cidr,
            )

        // When
        val result = network.toData()

        // Then
        assertEquals(IPFixture.ipv4.ipAddress, result.ip!!.address)
        assertEquals(NetworkFixture.networkName.value, result.name)
        assertEquals(NetworkFixture.cidr.value.toInt(), result.cidr)
    }

    @Test
    fun `fromData should map list of NetworkData to MutableList of Network`() {
        // Given
        val networkDataList =
            listOf(
                NetworkData(
                    ip = IPData(IPFixture.IPV4),
                    name = NetworkFixture.networkName.value,
                    cidr = NetworkFixture.cidr.value.toInt(),
                ),
                NetworkData(
                    ip = IPData(IPFixture.IPV6),
                    name = "TestNetwork2",
                    cidr = 24,
                ),
            )

        // When
        val result = networkDataList.fromData()

        // Then
        assertEquals(2, result.size)

        assertEquals(IPFixture.ipv4, result[0].address)
        assertEquals(NetworkFixture.networkName, result[0].name)
        assertEquals(NetworkFixture.cidr, result[0].cidr)

        assertEquals(IPFixture.ipv6, result[1].address)
        assertEquals(NetworkName("TestNetwork2"), result[1].name)
        assertEquals(CIDR(24u), result[1].cidr)
    }

    @Test
    fun `toData should map list of Network to list of NetworkData`() {
        // Given
        val networkList =
            mutableListOf(
                Network(
                    address = IPFixture.ipv4,
                    name = NetworkFixture.networkName,
                    cidr = NetworkFixture.cidr,
                ),
                Network(
                    address = IPFixture.ipv6,
                    name = NetworkName("TestNetwork2"),
                    cidr = CIDR(24u),
                ),
            )

        // When
        val result = networkList.toData()

        // Then
        assertEquals(2, result.size)

        assertEquals(IPFixture.IPV4, result[0].ip!!.address)
        assertEquals(NetworkFixture.networkName.value, result[0].name)
        assertEquals(NetworkFixture.cidr.value.toInt(), result[0].cidr)

        assertEquals(IPFixture.IPV6, result[1].ip!!.address)
        assertEquals("TestNetwork2", result[1].name)
        assertEquals(24, result[1].cidr)
    }
}
