package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.routers.PersistRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class AddRouterUseCaseTest {
    @Test
    fun `invoke should add router to core router and return the updated core router`() {
        // Given
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val addedRouter = mockk<CoreRouter>()

        val persistRouterPort = mockk<PersistRouterPort>()
        val addRouterUseCase = AddRouterUseCase(persistRouterPort)

        every { coreRouter.addRouter(router) } returns addedRouter
        every { persistRouterPort(addedRouter) } returns addedRouter

        // When
        val result = addRouterUseCase(router, coreRouter)

        // Then
        assertEquals(addedRouter, result)
        verify(exactly = 1) { coreRouter.addRouter(router) }
        verify(exactly = 1) { persistRouterPort(addedRouter) }
    }
}
