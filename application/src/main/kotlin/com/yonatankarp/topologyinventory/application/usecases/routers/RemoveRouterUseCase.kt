package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.RemoveRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.application.ports.output.routers.RemoveRouterPort as RemoveRouterOutputPort

class RemoveRouterUseCase(
    private val removeOutputPort: RemoveRouterOutputPort,
) : RemoveRouterPort {
    override fun invoke(id: Id): Router? = removeOutputPort(id)

    override fun fromCoreRouter(
        router: Router,
        coreRouter: CoreRouter,
    ): Router? {
        val removedRouter = coreRouter.removeRouter(router)
        return removedRouter?.also { removeOutputPort(it.id) }
    }
}
