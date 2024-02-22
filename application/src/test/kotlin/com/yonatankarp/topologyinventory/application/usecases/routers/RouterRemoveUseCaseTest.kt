package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.RouterRemoveOutputPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RouterRemoveUseCaseTest {
    @Test
    fun `invoke should remove router from core router and return the removed router`() {
        // Given
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val removedRouter = mockk<Router>()

        val routerRemoveOutputPort = mockk<RouterRemoveOutputPort>()
        val routerRemoveUseCase = RouterRemoveUseCase(routerRemoveOutputPort)

        every { coreRouter.removeRouter(router) } returns removedRouter

        // When
        val result = routerRemoveUseCase(router, coreRouter)

        // Then
        assertEquals(removedRouter, result)
        verify(exactly = 1) { coreRouter.removeRouter(router) }
    }
}
