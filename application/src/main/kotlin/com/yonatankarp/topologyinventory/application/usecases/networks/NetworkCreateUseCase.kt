package com.yonatankarp.topologyinventory.application.usecases.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.NetworkCreateInputPort
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

class NetworkCreateUseCase : NetworkCreateInputPort {
    override fun invoke(
        networkAddress: IP,
        networkName: NetworkName,
        networkCidr: CIDR,
    ) = Network(
        address = networkAddress,
        name = networkName,
        cidr = networkCidr,
    )
}
