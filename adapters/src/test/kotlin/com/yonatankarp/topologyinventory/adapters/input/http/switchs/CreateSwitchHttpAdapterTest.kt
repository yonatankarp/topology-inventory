package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.AddSwitchPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.CreateSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class CreateSwitchHttpAdapterTest {
    private val createSwitchPort = mockk<CreateSwitchPort>()
    private val addSwitchPort = mockk<AddSwitchPort>()
    private val getRouterPort = mockk<GetRouterPort>()
    private val addRouterPort = mockk<AddRouterPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should create switch and add to edge router`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val switchType = SwitchType.LAYER3
        val routerId = Id.withoutId()
        val newSwitch = mockk<Switch>()
        val edgeRouter = mockk<EdgeRouter>()
        val persistedEdgeRouter = mockk<EdgeRouter>()

        every { createSwitchPort(vendor, model, ip, location, switchType, routerId) } returns newSwitch
        every { getRouterPort(routerId) } returns edgeRouter
        every { edgeRouter.routerType } returns RouterType.EDGE
        every { addSwitchPort(newSwitch, edgeRouter) } returns edgeRouter
        every { addRouterPort.persist(edgeRouter) } returns persistedEdgeRouter

        val createSwitchHttpAdapter = CreateSwitchHttpAdapter(createSwitchPort, addSwitchPort, getRouterPort, addRouterPort)

        // When
        val result = createSwitchHttpAdapter(vendor, model, ip, location, switchType, routerId)

        // Then
        assertEquals(persistedEdgeRouter, result)
    }

    @Test
    fun `invoke should throw UnsupportedOperationException when router type is not EDGE`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val switchType = SwitchType.LAYER3
        val routerId = Id.withoutId()
        val newSwitch = mockk<Switch>()
        val coreRouter = mockk<CoreRouter>()

        every { createSwitchPort(vendor, model, ip, location, switchType, routerId) } returns newSwitch
        every { getRouterPort(routerId) } returns coreRouter
        every { coreRouter.routerType } returns RouterType.CORE

        val createSwitchHttpAdapter = CreateSwitchHttpAdapter(createSwitchPort, addSwitchPort, getRouterPort, addRouterPort)

        // Then
        assertThrows(UnsupportedOperationException::class.java) {
            // When
            createSwitchHttpAdapter(vendor, model, ip, location, switchType, routerId)
        }
    }
}
