package com.yonatankarp.topologyinventory.application.ports.input.networks

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

fun interface RemoveNetworkPort {
    operator fun invoke(
        networkName: NetworkName,
        networkSwitch: Switch,
    ): Switch?
}
