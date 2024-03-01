package com.yonatankarp.topologyinventory.adapters.framework

import com.yonatankarp.topologyinventory.adapters.input.http.routers.AddRouterHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.routers.CreateRouterHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.routers.GetRouterHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.routers.RemoveRouterHttpAdapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.PersistRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.RemoveRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.RetrieveRouterH2Adapter
import com.yonatankarp.topologyinventory.application.usecases.routers.AddRouterUseCase
import com.yonatankarp.topologyinventory.application.usecases.routers.CreateRouterUseCase
import com.yonatankarp.topologyinventory.application.usecases.routers.GetRouterUseCase
import com.yonatankarp.topologyinventory.application.usecases.routers.RemoveRouterUseCase
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class RouterFrameworkTest : FrameworkTestData() {
    private val entityManager = mockk<EntityManager>(relaxed = true)

    private val persistRouterOutputPort = PersistRouterH2Adapter(entityManager)
    private val getRouterOutputPort = RetrieveRouterH2Adapter(entityManager)
    private val removeRouterOutputPort = RemoveRouterH2Adapter(entityManager)

    private val getRouterPort = GetRouterUseCase(getRouterOutputPort)
    private val addRouterPort = AddRouterUseCase(persistRouterOutputPort)
    private val createRouterPort = CreateRouterUseCase()
    private val removeRouterPort = RemoveRouterUseCase(removeRouterOutputPort)

    private val addRouterHttpArray =
        AddRouterHttpAdapter(getRouterPort, addRouterPort)
    private val createRouterHttpAdapter =
        CreateRouterHttpAdapter(createRouterPort, persistRouterOutputPort)
    private val getRouterHttpAdapter = GetRouterHttpAdapter(getRouterPort)
    private val removeRouterHttpAdapter =
        RemoveRouterHttpAdapter(getRouterPort, removeRouterPort)

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `should retrieve a router`() {
        // Given
        val routerId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0c")
        val routerData = coreRouter.toData().copy(routerId = routerId.value)
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val actual = getRouterHttpAdapter(routerId)

        // Then
        assertNotNull(actual)
        assertEquals(routerId, actual.id)
    }

    @Test
    fun `should create a router`() {
        // Given
        val ipAddress = "40.0.0.1"
        val router =
            createRouterHttpAdapter(
                Vendor.DLINK,
                Model.XYZ0001,
                IP(ipAddress),
                locationA,
                RouterType.EDGE,
            )

        val routerData = router.toData()
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val newRouter = getRouterHttpAdapter(router.id)

        // Then
        assertNotNull(newRouter)
        assertEquals(router.id, newRouter.id)
        assertEquals(Vendor.DLINK, newRouter.vendor)
        assertEquals(Model.XYZ0001, newRouter.model)
        assertEquals(ipAddress, newRouter.ip.ipAddress)
        assertEquals(locationA, newRouter.location)
        assertEquals(RouterType.EDGE, newRouter.routerType)
    }

    @Test
    fun `should add a router to core router`() {
        // Given
        val routerId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0b")
        val coreRouterId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0c")

        val routerData = newCoreRouter.toData().copy(routerId = routerId.value)
        val coreRouterData =
            coreRouter.toData().copy(routerId = coreRouterId.value)
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData andThen coreRouterData

        // When
        val actualRouter =
            addRouterHttpArray(routerId, coreRouterId) as? CoreRouter

        // Then
        assertNotNull(actualRouter)
        assertEquals(routerId, actualRouter.routers[routerId]?.id)
    }

    @Test
    fun `should remove a router from core router`() {
        // Given
        val routerId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0a")
        val coreRouterId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0c")

        val routerData =
            edgeRouter.toData()
                .copy(routerId = routerId.value, switches = emptyList())
        val coreRouterData =
            coreRouter.toData().copy(
                routerId = coreRouterId.value,
                routers = listOf(routerData),
            )
        val coreRouterDataWithoutRouter = coreRouterData.copy(routers = emptyList())
        every {
            entityManager.getReference<RouterData>(any(), any())
        } returnsMany listOf(routerData, coreRouterData, coreRouterDataWithoutRouter)

        // When
        val removedRouter =
            removeRouterHttpAdapter.fromCoreRouter(routerId, coreRouterId)

        // Then
        val coreRouter = getRouterHttpAdapter(coreRouterId) as CoreRouter

        assertNotNull(removedRouter)
        assertNotNull(coreRouter)
        assertEquals(routerId, removedRouter.id)
        assertFalse(routerId in coreRouter.routers.keys)
    }

    @Test
    fun `should remove a router`() {
        // Given
        val routerId = Id.withId("b832ef4f-f894-4194-8feb-a99c2cd4be0b")
        val routerData = coreRouter.toData().copy(routerId = routerId.value)
        every {
            entityManager.getReference<RouterData>(
                any(),
                any(),
            )
        } returns routerData

        // When
        val router = removeRouterHttpAdapter(routerId)

        // Then
        assertNotNull(router)
    }
}
