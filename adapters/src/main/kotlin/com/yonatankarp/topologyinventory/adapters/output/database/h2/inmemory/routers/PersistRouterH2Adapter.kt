package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.routers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toData
import com.yonatankarp.topologyinventory.application.ports.output.routers.PersistRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class PersistRouterH2Adapter(
    private val entityManager: EntityManager,
) : PersistRouterPort {
    override fun invoke(router: Router): Router = router.also { entityManager.persist(router.toData()) }
}
