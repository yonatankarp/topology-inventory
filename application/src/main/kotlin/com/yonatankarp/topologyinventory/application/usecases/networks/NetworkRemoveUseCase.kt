package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.NetworkRemoveInputPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Network

class NetworkRemoveUseCase : NetworkRemoveInputPort {
    override fun invoke(
        network: Network,
        networkSwitch: Switch,
    ): Switch {
        networkSwitch.removeNetworkFromSwitch(network)
        return networkSwitch
    }
}
