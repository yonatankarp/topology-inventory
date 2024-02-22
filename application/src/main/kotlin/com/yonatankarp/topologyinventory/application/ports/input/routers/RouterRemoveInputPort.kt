package com.yonatankarp.topologyinventory.application.ports.input.routers

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

fun interface RouterRemoveInputPort {
    operator fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): Router?
}
