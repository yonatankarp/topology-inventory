package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.RouterAddOutputPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RouterAddUseCaseTest {
    @Test
    fun `invoke should add router to core router and return the updated core router`() {
        // Given
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val addedRouter = mockk<CoreRouter>()

        val routerAddOutputPort = mockk<RouterAddOutputPort>()
        val routerAddUseCase = RouterAddUseCase(routerAddOutputPort)

        every { coreRouter.addRouter(router) } returns addedRouter
        every { routerAddOutputPort.persistRouter(addedRouter) } returns addedRouter

        // When
        val result = routerAddUseCase(router, coreRouter)

        // Then
        assertEquals(addedRouter, result)
        verify(exactly = 1) { coreRouter.addRouter(router) }
        verify(exactly = 1) { routerAddOutputPort.persistRouter(addedRouter) }
    }
}
