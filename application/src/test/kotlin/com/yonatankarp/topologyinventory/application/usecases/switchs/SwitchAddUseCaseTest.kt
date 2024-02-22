package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class SwitchAddUseCaseTest {
    @Test
    fun `invoke should add switch to edge router and return the edge router`() {
        // Given
        val networkSwitch = mockk<Switch>()
        val edgeRouter = mockk<EdgeRouter>()
        val switchAddUseCase = SwitchAddUseCase()

        val location = LocationFixture.createLocation("US")
        every { networkSwitch.location } returns location
        every { networkSwitch.ip } returns IPFixture.ipv4
        every { networkSwitch.id } returns Id.withoutId()
        every { edgeRouter.location } returns location
        every { edgeRouter.addSwitch(networkSwitch) } returns Unit

        // When
        switchAddUseCase(networkSwitch, edgeRouter)

        // Then
        verify(exactly = 1) { edgeRouter.addSwitch(networkSwitch) }
    }
}
