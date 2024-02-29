package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.routers.RemoveRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RemoveRouterUseCaseTest {
    @Test
    fun `invoke should remove router from core router and return the removed router`() {
        // Given
        val router = mockk<Router>()
        val coreRouter = mockk<CoreRouter>()
        val removedRouter = mockk<Router>()

        val removeRouterPort = mockk<RemoveRouterPort>()
        val removeRouterUseCase = RemoveRouterUseCase(removeRouterPort)

        every { removedRouter.id } returns Id.withoutId()
        every { removeRouterPort.invoke(any()) } returns removedRouter
        every { coreRouter.removeRouter(router) } returns removedRouter

        // When
        val result = removeRouterUseCase.fromCoreRouter(router, coreRouter)

        // Then
        assertEquals(removedRouter, result)
        verify(exactly = 1) { coreRouter.removeRouter(router) }
    }
}
