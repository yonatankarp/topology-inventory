package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class GetRouterUseCaseTest {
    @Test
    fun `invoke should retrieve router with given id`() {
        // Given
        val id = Id.withId("51a4632c-6f46-44b2-b0bc-836668f046f3")
        val router = mockk<Router>()

        val getRouterPort = mockk<GetRouterPort>()
        val getRouterUseCase = GetRouterUseCase(getRouterPort)

        every { getRouterPort(id) } returns router

        // When
        val result = getRouterUseCase(id)

        // Then
        assertEquals(router, result)
    }
}
