package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.output.routers.PersistRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

class AddRouterUseCase(
    private val persistRouterPort: PersistRouterPort,
) : AddRouterPort {
    override fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): CoreRouter {
        val addedRouter = coreRouter.addRouter(router)
        persist(addedRouter)
        return addedRouter
    }

    override fun persist(router: Router): Router = persistRouterPort(router)
}
