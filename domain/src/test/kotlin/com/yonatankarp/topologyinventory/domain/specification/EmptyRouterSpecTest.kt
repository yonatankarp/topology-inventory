package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EmptyRouterSpecTest {
    @Test
    fun `core router with empty list of routers satisfies specification`() {
        // Given
        val emptyRouterSpec = EmptyRouterSpec()
        val coreRouter = RouterFixture.coreRouter

        // When
        val result = emptyRouterSpec.isSatisfiedBy(coreRouter)

        // Then
        assertTrue(result)
        assertDoesNotThrow { emptyRouterSpec.check(coreRouter) }
    }

    @Test
    fun `core router with non-empty list of routers does not satisfy specification`() {
        // Given
        val emptyRouterSpec = EmptyRouterSpec()
        val coreRouter = RouterFixture.coreRouter
        coreRouter.addRouter(RouterFixture.edgeRouter)

        // When
        val result = emptyRouterSpec.isSatisfiedBy(coreRouter)

        // Then
        assertFalse(result)
        assertThrows(GenericSpecificationException::class.java) {
            emptyRouterSpec.check(coreRouter)
        }
    }
}
