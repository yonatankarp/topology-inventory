package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.valueobject.Network

object NetworkService {
    fun filterAndRetrieveNetworks(
        networks: List<Network>,
        predicate: (Network) -> Boolean,
    ): List<Network> = networks.filter(predicate).toList()
}
