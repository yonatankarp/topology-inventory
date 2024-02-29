package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.CreateRouterPort
import com.yonatankarp.topologyinventory.application.ports.output.routers.PersistRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class CreateRouterHttpAdapterTest {
    private val createRouterPort = mockk<CreateRouterPort>()
    private val persistRouterPort = mockk<PersistRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should create and persist router`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val routerType = RouterType.CORE

        val createdRouter = mockk<Router>()
        val persistedRouter = mockk<Router>()

        every { createRouterPort(vendor, model, ip, location, routerType) } returns createdRouter
        every { persistRouterPort(createdRouter) } returns persistedRouter

        val createRouterHttpAdapter = CreateRouterHttpAdapter(createRouterPort, persistRouterPort)

        // When
        val result = createRouterHttpAdapter(vendor, model, ip, location, routerType)

        // Then
        assertEquals(persistedRouter, result)
    }
}
