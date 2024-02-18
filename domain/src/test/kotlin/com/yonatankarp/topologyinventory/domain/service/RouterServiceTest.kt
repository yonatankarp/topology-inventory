package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.entity.Equipment
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.entity.RouterFixture.createCoreRouter
import com.yonatankarp.topologyinventory.domain.entity.RouterFixture.createEdgeRouter
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture.createLocation
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RouterServiceTest {
    @Test
    fun `filter router by type CORE`() {
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val edgeRouter = createEdgeRouter(location, "40.0.0.1")
        val routers = listOf(coreRouter, edgeRouter)

        val coreRouters =
            RouterService.filterAndRetrieveRouter(
                routers,
                Router.getRouterTypePredicate(RouterType.CORE),
            )
        val actualCoreType = coreRouters.first().routerType
        assertEquals(RouterType.CORE, actualCoreType)
    }

    @Test
    fun `filter router by type EDGE`() {
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val edgeRouter = createEdgeRouter(location, "40.0.0.1")
        val routers = listOf(coreRouter, edgeRouter)

        val edgeRouters =
            RouterService.filterAndRetrieveRouter(
                routers,
                Router.getRouterTypePredicate(RouterType.EDGE),
            )
        val actualEdgeType = edgeRouters.first().routerType
        assertEquals(RouterType.EDGE, actualEdgeType)
    }

    @Test
    fun `filter router by vendor`() {
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val edgeRouter = createEdgeRouter(location, "40.0.0.1")
        val routers = listOf(coreRouter, edgeRouter)

        var actualVendor =
            RouterService.filterAndRetrieveRouter(
                routers,
                Equipment.getVendorPredicate(Vendor.HP),
            )
        assertEquals(Vendor.HP, actualVendor.first().vendor)

        actualVendor =
            RouterService.filterAndRetrieveRouter(
                routers,
                Equipment.getVendorPredicate(Vendor.CISCO),
            )
        assertEquals(Vendor.CISCO, actualVendor.first().vendor)
    }

    @Test
    fun filterRouterByLocation() {
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val routers = listOf(coreRouter)

        val actualCountry =
            RouterService.filterAndRetrieveRouter(
                routers,
                Router.getCountryPredicate(location),
            )
        assertEquals(location.country, actualCountry.first().location.country)
    }

    @Test
    fun filterRouterByModel() {
        val location = createLocation("US")
        val coreRouter = createCoreRouter(location, "30.0.0.1")
        val newCoreRouter = createCoreRouter(location, "40.0.0.1")

        coreRouter.addRouter(newCoreRouter)
        val routers = listOf(coreRouter)

        val actualModel =
            RouterService.filterAndRetrieveRouter(
                routers,
                Router.getModelPredicate(Model.XYZ0001),
            )
        assertEquals(Model.XYZ0001, actualModel.first().model)
    }
}
