package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.RouterAddInputPort
import com.yonatankarp.topologyinventory.application.ports.output.RouterAddOutputPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

class RouterAddUseCase(
    private val routerAddOutputPort: RouterAddOutputPort,
) : RouterAddInputPort {
    override fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): CoreRouter {
        val addedRouter = coreRouter.addRouter(router)
        persist(addedRouter)
        return addedRouter
    }

    override fun persist(router: Router): Router = routerAddOutputPort.persistRouter(router)
}
