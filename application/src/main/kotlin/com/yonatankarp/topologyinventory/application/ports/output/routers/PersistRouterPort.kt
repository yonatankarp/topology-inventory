package com.yonatankarp.topologyinventory.application.ports.output.routers

import com.yonatankarp.topologyinventory.domain.entity.Router

fun interface PersistRouterPort {
    operator fun invoke(router: Router): Router
}
