package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification
import com.yonatankarp.topologyinventory.domain.valueobject.Network

class NetworkAvailabilitySpec(
    private val network: Network,
) : AbstractSpecification<Switch>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(switchNetworks: Switch) = isNetworkAvailable(switchNetworks)

    private fun isNetworkAvailable(switchNetworks: Switch): Boolean =
        switchNetworks.switchNetworks.none {
            (it.address == network.address) && (it.name == network.name) && (it.cidr == network.cidr)
        }

    override fun check(t: Switch) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("This network already exists")
        }
    }
}
