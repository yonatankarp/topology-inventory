package com.yonatankarp.topologyinventory.application.usecases.network

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Network

interface NetworkAddUseCase {
    operator fun invoke(
        network: Network,
        networkSwitch: Switch,
    ): Switch
}
