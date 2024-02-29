package com.yonatankarp.topologyinventory.application.ports.input.networks

import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

fun interface CreateNetworkPort {
    operator fun invoke(
        networkAddress: IP,
        networkName: NetworkName,
        networkCidr: CIDR,
    ): Network
}
