package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toDomain
import com.yonatankarp.topologyinventory.application.ports.output.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import jakarta.persistence.EntityManager

class RetrieveRouterH2Adapter(
    private val entityManager: EntityManager,
) : GetRouterPort {
    override fun invoke(id: Id): Router? =
        entityManager
            .getReference(RouterData::class.java, id.value)
            ?.toDomain()
}
