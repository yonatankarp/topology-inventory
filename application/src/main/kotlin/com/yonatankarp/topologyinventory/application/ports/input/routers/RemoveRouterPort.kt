package com.yonatankarp.topologyinventory.application.ports.input.routers

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

interface RemoveRouterPort {
    operator fun invoke(id: Id): Router?

    fun fromCoreRouter(
        router: Router,
        coreRouter: CoreRouter,
    ): Router?
}
