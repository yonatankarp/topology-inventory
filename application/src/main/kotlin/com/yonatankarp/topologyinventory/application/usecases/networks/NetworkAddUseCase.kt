package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.NetworkAddInputPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Network

class NetworkAddUseCase : NetworkAddInputPort {
    override fun invoke(
        network: Network,
        networkSwitch: Switch,
    ): Switch {
        networkSwitch.addNetworkToSwitch(network)
        return networkSwitch
    }
}
