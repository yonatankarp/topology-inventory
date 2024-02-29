package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.AddNetworkPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Network

class AddNetworkUseCase : AddNetworkPort {
    override fun invoke(
        network: Network,
        networkSwitch: Switch,
    ): Switch {
        networkSwitch.addNetworkToSwitch(network)
        return networkSwitch
    }
}
