package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.output.RouterCreateOutputPort
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class RouterCreateUseCaseTest {
    @Test
    fun `invoke should create a router with given parameters`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val routerType = RouterType.CORE

        val routerCreateOutputPort = mockk<RouterCreateOutputPort>()
        val routerCreateUseCase = RouterCreateUseCase(routerCreateOutputPort)

        // When
        val result = routerCreateUseCase(vendor, model, ip, location, routerType)

        // Then
        assertEquals(vendor, result.vendor)
        assertEquals(model, result.model)
        assertEquals(ip, result.ip)
        assertEquals(location, result.location)
        assertEquals(routerType, result.routerType)
    }
}
