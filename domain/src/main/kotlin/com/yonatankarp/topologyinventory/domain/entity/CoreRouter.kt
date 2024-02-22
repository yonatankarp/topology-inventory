package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.specification.EmptyRouterSpec
import com.yonatankarp.topologyinventory.domain.specification.EmptySwitchSpec
import com.yonatankarp.topologyinventory.domain.specification.SameCountrySpec
import com.yonatankarp.topologyinventory.domain.specification.SameIpSpec
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType.CORE
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType.EDGE
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class CoreRouter(
    id: Id,
    vendor: Vendor,
    model: Model,
    ip: IP,
    location: Location,
    routerType: RouterType,
    private val _routers: MutableMap<Id, Router> = mutableMapOf(),
) : Router(id, vendor, model, ip, location, routerType) {
    val routers: Map<Id, Router>
        get() = _routers.toMap()

    fun addRouter(anyRouter: Router): CoreRouter {
        val sameCountryRouterSpec = SameCountrySpec(this)
        val sameIpSpec = SameIpSpec(this)

        sameCountryRouterSpec.check(anyRouter)
        sameIpSpec.check(anyRouter)

        _routers[anyRouter.id] = anyRouter

        return this
    }

    fun removeRouter(anyRouter: Router): Router? {
        val emptyRoutersSpec = EmptyRouterSpec()
        val emptySwitchSpec = EmptySwitchSpec()

        when (anyRouter.routerType) {
            CORE -> {
                val coreRouter = anyRouter as CoreRouter
                emptyRoutersSpec.check(coreRouter)
            }

            EDGE -> {
                val edgeRouter = anyRouter as EdgeRouter
                emptySwitchSpec.check(edgeRouter)
            }
        }

        return _routers.remove(anyRouter.id)
    }
}
