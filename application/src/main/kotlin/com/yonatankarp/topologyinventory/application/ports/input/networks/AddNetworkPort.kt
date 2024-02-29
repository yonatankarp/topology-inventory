package com.yonatankarp.topologyinventory.application.ports.input.networks

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Network

fun interface AddNetworkPort {
    operator fun invoke(
        network: Network,
        networkSwitch: Switch,
    ): Switch
}
