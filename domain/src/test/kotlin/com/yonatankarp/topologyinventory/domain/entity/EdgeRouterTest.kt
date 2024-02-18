package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture.createEdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture.createSwitch
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture.createLocation
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture.createNetwork
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class EdgeRouterTest {
    @Test
    fun `add switch to edge router`() {
        // Given
        val location = createLocation("US")
        val networkSwitch = createSwitch("30.0.0.0", 8u, location)
        val edgeRouter = createEdgeRouter(location, "30.0.0.1")

        // When
        edgeRouter.addSwitch(networkSwitch)

        // Then
        assertEquals(1, edgeRouter.switches.size)
    }

    @Test
    fun `add switch to edge router - fail because equipment of different countries`() {
        // Given
        val locationUS = createLocation("US")
        val locationJP = createLocation("JP")
        val networkSwitch = createSwitch("30.0.0.0", 8u, locationUS)
        val edgeRouter = createEdgeRouter(locationJP, "30.0.0.1")

        // Then
        assertThrows(GenericSpecificationException::class.java) {
            // When
            edgeRouter.addSwitch(networkSwitch)
        }
    }

    @Test
    fun `remove switch`() {
        // Given
        val location = createLocation("US")
        val network = createNetwork("30.0.0.0", 8u)
        val networkSwitch = createSwitch("30.0.0.0", 8u, location)
        val edgeRouter = createEdgeRouter(location, "40.0.0.1")
        edgeRouter.addSwitch(networkSwitch)

        // When
        networkSwitch.removeNetworkFromSwitch(network)

        // Then
        val expectedId = Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")
        val actualId = edgeRouter.removeSwitch(networkSwitch)?.id
        assertEquals(expectedId, actualId)
    }
}
