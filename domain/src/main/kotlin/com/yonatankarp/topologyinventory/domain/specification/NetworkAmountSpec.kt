package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class NetworkAmountSpec : AbstractSpecification<Switch>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(switchNetwork: Switch) = switchNetwork.switchNetworks.size <= MAXIMUM_ALLOWED_NETWORKS

    @Throws(GenericSpecificationException::class)
    override fun check(t: Switch) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("The max number of networks is $MAXIMUM_ALLOWED_NETWORKS")
        }
    }

    companion object {
        private const val MAXIMUM_ALLOWED_NETWORKS = 6
    }
}
