package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class EmptySwitchSpec : AbstractSpecification<EdgeRouter>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(edgeRouter: EdgeRouter) = edgeRouter.switches.isEmpty()

    override fun check(t: EdgeRouter) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("It isn't allowed to remove an edge router with a switch attached to it")
        }
    }
}
