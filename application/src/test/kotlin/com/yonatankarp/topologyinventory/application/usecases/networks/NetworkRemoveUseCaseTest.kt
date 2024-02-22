package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class NetworkRemoveUseCaseTest {
    @Test
    fun `invoke should remove network from switch and return the switch`() {
        // Given
        val network = NetworkFixture.network
        val networkSwitch = mockk<Switch>()
        every { networkSwitch.removeNetworkFromSwitch(network) } returns true
        val networkRemoveUseCase = NetworkRemoveUseCase()

        // When
        val result = networkRemoveUseCase(network, networkSwitch)

        // Then
        assertEquals(networkSwitch, result)
    }
}
