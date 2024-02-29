package com.yonatankarp.topologyinventory.application.ports.output.routers

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

fun interface RemoveRouterPort {
    operator fun invoke(id: Id): Router?
}
