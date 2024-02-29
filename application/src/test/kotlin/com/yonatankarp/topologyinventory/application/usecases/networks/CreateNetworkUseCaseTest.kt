package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class CreateNetworkUseCaseTest {
    @Test
    fun `invoke should create a network with given parameters`() {
        // Given
        val networkAddress = IPFixture.ipv4
        val networkName = NetworkFixture.networkName
        val networkCidr = NetworkFixture.cidr
        val createNetworkUseCase = CreateNetworkUseCase()

        // When
        val result =
            createNetworkUseCase(
                networkAddress,
                networkName,
                networkCidr,
            )

        // Then
        val expected = NetworkFixture.network
        assertEquals(expected, result)
    }
}
