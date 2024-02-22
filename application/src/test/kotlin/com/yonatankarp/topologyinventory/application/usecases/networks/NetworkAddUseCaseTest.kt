package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class NetworkAddUseCaseTest {
    @Test
    fun `invoke should add network to switch and return the switch`() {
        // Given
        val network = NetworkFixture.network
        val networkSwitch = mockk<Switch>()
        every { networkSwitch.addNetworkToSwitch(network) } returns true
        val networkAddUseCase = NetworkAddUseCase()

        // When
        val result = networkAddUseCase(network, networkSwitch)

        // Then
        assertEquals(networkSwitch, result)
        verify(exactly = 1) { networkSwitch.addNetworkToSwitch(network) }
    }
}
