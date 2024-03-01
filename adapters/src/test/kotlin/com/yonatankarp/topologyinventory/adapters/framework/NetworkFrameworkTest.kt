package com.yonatankarp.topologyinventory.adapters.framework

import com.yonatankarp.topologyinventory.adapters.input.http.networks.AddNetworkHttpAdapter
import com.yonatankarp.topologyinventory.adapters.input.http.networks.RemoveNetworkHttpAdapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.PersistRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers.RetrieveRouterH2Adapter
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.switches.RetrieveSwitchH2Adapter
import com.yonatankarp.topologyinventory.application.usecases.networks.AddNetworkUseCase
import com.yonatankarp.topologyinventory.application.usecases.networks.RemoveNetworkUseCase
import com.yonatankarp.topologyinventory.application.usecases.switchs.GetSwitchUseCase
import com.yonatankarp.topologyinventory.domain.service.NetworkService
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class NetworkFrameworkTest : FrameworkTestData() {
    private val entityManager = mockk<EntityManager>()

    private val retrieveSwitchOutputPort =
        RetrieveSwitchH2Adapter(entityManager)
    private val retrieveRouterOutputAdapter =
        RetrieveRouterH2Adapter(entityManager)
    private val persistRouterOutputAdapter =
        PersistRouterH2Adapter(entityManager)
    private val addRouterOutputPort = RetrieveRouterH2Adapter(entityManager)

    private val getSwitchPort = GetSwitchUseCase(retrieveSwitchOutputPort)
    private val addNetworkPort = AddNetworkUseCase()
    private val removeNetworkPort =
        RemoveNetworkUseCase(
            retrieveRouterOutputAdapter,
            persistRouterOutputAdapter,
        )

    private val addNetworkAdapter =
        AddNetworkHttpAdapter(getSwitchPort, addNetworkPort)
    private val removeNetworkAdapter =
        RemoveNetworkHttpAdapter(getSwitchPort, removeNetworkPort)

    @Test
    @Order(1)
    fun `should add network to switch`() {
        // Given
        every {
            entityManager.getReference<SwitchData>(
                any(),
                any(),
            )
        } returns networkSwitch.toData()

        // When
        val networkSwitch = addNetworkAdapter(newNetwork, networkSwitch.id)

        // Then
        val predicate =
            Network.getNetworkNamePredicate(NetworkName("NewNetwork"))
        val actualNetwork = NetworkService.filterAndRetrieveNetworks(networkSwitch.switchNetworks, predicate)
        assertEquals(1, actualNetwork.size)
        assertEquals(newNetwork, actualNetwork.first())
    }

//    @Test
//    @Order(2)
//    fun removeNetworkFromSwitch() {
//        val switchId: Id = Id.withId("922dbcd5-d071-41bd-920b-00f83eb4bb46")
//        val networkName = "HR"
//        val predicate: Predicate<Network> =
//            Network.getNetworkNamePredicate(networkName)
//        var networkSwitch: Switch =
//            switchManagementGenericAdapter.retrieveSwitch(switchId)
//        val existentNetwork: Network = NetworkService.findNetwork(
//            networkSwitch.getSwitchNetworks(),
//            predicate
//        )
//        Assertions.assertNotNull(existentNetwork)
//        networkSwitch = networkManagementGenericAdapter.removeNetworkFromSwitch(
//            networkName,
//            switchId
//        )
//        Assertions.assertNull(networkSwitch)
//    }
}
