package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.RouterGetOutputPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RouterGetUseCaseTest {
    @Test
    fun `invoke should retrieve router with given id`() {
        // Given
        val id = Id.withId("51a4632c-6f46-44b2-b0bc-836668f046f3")
        val router = mockk<Router>()

        val routerGetOutputPort = mockk<RouterGetOutputPort>()
        val routerGetUseCase = RouterGetUseCase(routerGetOutputPort)

        every { routerGetOutputPort.retrieveRouter(id) } returns router

        // When
        val result = routerGetUseCase(id)

        // Then
        assertEquals(router, result)
    }
}
