package com.yonatankarp.topologyinventory.application.ports.input.routers

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

fun interface RouterGetInputPort {
    operator fun invoke(id: Id): Router
}
