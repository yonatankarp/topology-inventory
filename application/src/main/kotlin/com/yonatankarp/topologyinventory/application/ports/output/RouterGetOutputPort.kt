package com.yonatankarp.topologyinventory.application.ports.output

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

interface RouterGetOutputPort {
    fun retrieveRouter(id: Id): Router
}
