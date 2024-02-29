package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.RemoveNetworkPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.output.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.service.NetworkService
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

class RemoveNetworkUseCase(
    private val getRouterPort: GetRouterPort,
    private val addRouterPort: AddRouterPort,
) : RemoveNetworkPort {
    override fun invoke(
        networkName: NetworkName,
        networkSwitch: Switch,
    ): Switch? {
        val routerId = networkSwitch.routerId
        val switchId = networkSwitch.id

        val edgeRouter =
            getRouterPort(networkSwitch.routerId) as? EdgeRouter
                ?: throw IllegalArgumentException("Router id $routerId does not exist")

        val switchToRemoveNetwork =
            edgeRouter.switches[networkSwitch.id]
                ?: throw IllegalArgumentException("Switch id $switchId does not exist")

        val network =
            NetworkService.filterAndRetrieveNetworks(
                switchToRemoveNetwork.switchNetworks,
                Network.getNetworkNetPredicate(networkName),
            )

        switchToRemoveNetwork.removeNetworksFromSwitch(network)

        addRouterPort.persist(edgeRouter)

        return if (switchToRemoveNetwork.removeNetworksFromSwitch(network)) {
            switchToRemoveNetwork
        } else {
            null
        }
    }
}
