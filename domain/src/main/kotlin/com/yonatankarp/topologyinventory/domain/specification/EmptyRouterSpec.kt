package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class EmptyRouterSpec : AbstractSpecification<CoreRouter>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(coreRouter: CoreRouter) = coreRouter.routers.isEmpty()

    override fun check(t: CoreRouter) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("It isn't allowed to remove a core router with other routers attached to it")
        }
    }
}
