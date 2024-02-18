package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class EmptyNetworkSpec : AbstractSpecification<Switch>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(switchNetwork: Switch) = switchNetwork.switchNetworks.isEmpty()

    override fun check(t: Switch) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("It's not possible to remove a switch with networks attached to it")
        }
    }
}
