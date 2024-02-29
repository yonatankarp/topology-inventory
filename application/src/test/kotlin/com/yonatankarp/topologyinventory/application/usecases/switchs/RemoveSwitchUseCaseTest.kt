package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RemoveSwitchUseCaseTest {
    @Test
    fun `removeFromEdgeRouter should remove switch from edge router and return the edge router`() {
        // Given
        val networkSwitch = mockk<Switch>()
        val edgeRouter = mockk<EdgeRouter>()
        val removeSwitchUseCase = RemoveSwitchUseCase()

        every { edgeRouter.removeSwitch(networkSwitch) } returns networkSwitch

        // When
        val result = removeSwitchUseCase(networkSwitch, edgeRouter)

        // Then
        assertEquals(edgeRouter, result)
        verify(exactly = 1) { edgeRouter.removeSwitch(networkSwitch) }
    }
}
