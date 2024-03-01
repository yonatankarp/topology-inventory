package com.yonatankarp.topologyinventory.adapters.framework

import com.yonatankarp.topologyinventory.adapters.input.http.switchs.CreateSwitchHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.switchs.GetSwitchHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.switchs.RemoveSwitchHttpAdapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.PersistRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.RetrieveRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.switches.RetrieveSwitchH2Adapter
import com.yonatankarp.topologyinventory.application.usecases.routers.AddRouterUseCase
import com.yonatankarp.topologyinventory.application.usecases.routers.GetRouterUseCase
import com.yonatankarp.topologyinventory.application.usecases.switchs.AddSwitchUseCase
import com.yonatankarp.topologyinventory.application.usecases.switchs.CreateSwitchUseCase
import com.yonatankarp.topologyinventory.application.usecases.switchs.GetSwitchUseCase
import com.yonatankarp.topologyinventory.application.usecases.switchs.RemoveSwitchUseCase
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SwitchFrameworkTest : FrameworkTestData() {
    private val entityManager = mockk<EntityManager>(relaxed = true)

    private val retrieveSwitchOutputAdapter =
        RetrieveSwitchH2Adapter(entityManager)
    private val retrieveRouterOutputAdapter =
        RetrieveRouterH2Adapter(entityManager)
    private val persistRouterOutputAdapter =
        PersistRouterH2Adapter(entityManager)

    private val createSwitchPort = CreateSwitchUseCase()
    private val addSwitchPort = AddSwitchUseCase()
    private val getSwitchPort = GetSwitchUseCase(retrieveSwitchOutputAdapter)
    private val removeSwitchPort = RemoveSwitchUseCase()

    private val getRouterPort = GetRouterUseCase(retrieveRouterOutputAdapter)
    private val addRouterPort = AddRouterUseCase(persistRouterOutputAdapter)

    private val createSwitchAdapter =
        CreateSwitchHttpAdapter(
            createSwitchPort,
            addSwitchPort,
            getRouterPort,
            addRouterPort,
        )
    private val getSwitchAdapter = GetSwitchHttpAdapter(getSwitchPort)
    private val removeSwitchAdapter =
        RemoveSwitchHttpAdapter(removeSwitchPort, getRouterPort, addRouterPort)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    @Order(1)
    fun `should retrieve a switch`() {
        // Given
        val switchId = emptyNetworkSwitch.id
        val routerId = edgeRouter.id
        val routerData =
            edgeRouter.toData()
                .copy(switches = listOf(emptyNetworkSwitch.toData()))
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val networkSwitch = removeSwitchAdapter(switchId, routerId)

        // Then
        assertNotNull(networkSwitch)
    }

    @Test
    @Order(2)
    fun `should create and add a switch to edge router`() {
        // Given
        val expectedSwitchIP = "15.0.0.1"
        val id = Id.withId("b07f5187-2d82-4975-a14b-bdbad9a8ad46")
        val routerData =
            edgeRouter.toData()
                .copy(switches = listOf(emptyNetworkSwitch.toData()))
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val edgeRouter =
            createSwitchAdapter(
                Vendor.HP,
                Model.XYZ0004,
                IP(expectedSwitchIP),
                locationA,
                SwitchType.LAYER3,
                id,
            )

        // Then
        val actualSwitchIP =
            edgeRouter.switches
                .map { it.value }
                .map { it.ip.ipAddress }
                .first { it == expectedSwitchIP }

        assertEquals(expectedSwitchIP, actualSwitchIP)
    }

    @Test
    @Order(3)
    fun `should remove switch from edge router`() {
        // Given
        val switchId = emptyNetworkSwitch.id
        val edgeRouterId = edgeRouter.id
        val routerData =
            edgeRouter.toData()
                .copy(switches = listOf(emptyNetworkSwitch.toData()))
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val edgeRouter = removeSwitchAdapter(switchId, edgeRouterId)

        // Then
        assertNull(edgeRouter.switches[switchId])
    }
}
