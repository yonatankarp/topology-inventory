package com.yonatankarp.topologyinventory.application.ports.input.routers

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

interface AddRouterPort {
    operator fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): CoreRouter

    fun persist(router: Router): Router
}
