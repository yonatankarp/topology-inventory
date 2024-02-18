package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.entity.Equipment
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

object RouterService {
    fun filterAndRetrieveRouter(
        routers: List<Router>,
        predicate: (Equipment) -> Boolean,
    ) = routers.filter(predicate).toList()

    fun findById(
        routers: Map<Id, Router>,
        id: Id,
    ): Router? = routers[id]
}
