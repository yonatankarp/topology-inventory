package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture.createCoreRouter
import com.yonatankarp.topologyinventory.domain.entity.RouterFixture.createEdgeRouter
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture.createLocation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class CoreRouterTest {
    @Test
    fun `add edge to core router`() {
        // Given
        val location = createLocation("US")
        val edgeRouter = createEdgeRouter(location, "30.0.0.1")
        val coreRouter = createCoreRouter(location, "40.0.0.1")

        // When
        coreRouter.addRouter(edgeRouter)

        // Then
        assertEquals(1, coreRouter.routers.size)
    }

    @Test
    fun `add edge to core router - fail because routers of different countries`() {
        // Given
        val locationUS = createLocation("US")
        val locationJP = createLocation("JP")
        val edgeRouter = createEdgeRouter(locationUS, "30.0.0.1")
        val coreRouter = createCoreRouter(locationJP, "40.0.0.1")

        // Then
        assertThrows(GenericSpecificationException::class.java) {
            // When
            coreRouter.addRouter(edgeRouter)
        }
    }

    @Test
    fun `add core to core router`() {
        // Given
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val newCoreRouter = createCoreRouter(location, "40.0.0.1")

        // When
        coreRouter.addRouter(newCoreRouter)

        // Then
        assertEquals(1, coreRouter.routers.size)
    }

    @Test
    fun `add core to core router - fail because routers of same ip`() {
        // Given
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val newCoreRouter = createCoreRouter(location, "30.0.0.1")

        // Then
        assertThrows(GenericSpecificationException::class.java) {
            // When
            coreRouter.addRouter(newCoreRouter)
        }
    }

    @Test
    fun `remove router`() {
        // Given
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val edgeRouter = createEdgeRouter(location, "40.0.0.1")
        val expectedId = edgeRouter.id

        // When
        coreRouter.addRouter(edgeRouter)

        // Then
        val actualId = coreRouter.removeRouter(edgeRouter)?.id
        assertEquals(expectedId, actualId)
    }
}
