package com.yonatankarp.topologyinventory.application.usecases.network

import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

interface NetworkCreateUseCase {
    operator fun invoke(
        networkAddress: IP,
        networkName: NetworkName,
        networkCidr: CIDR,
    ): Network
}
