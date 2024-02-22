package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.RouterRemoveInputPort
import com.yonatankarp.topologyinventory.application.ports.output.RouterRemoveOutputPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

class RouterRemoveUseCase(
    private val removeOutputPort: RouterRemoveOutputPort,
) : RouterRemoveInputPort {
    override fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): Router? {
        val removedRouter = coreRouter.removeRouter(router)
        // persistRouter(removedRouter)
        return removedRouter
    }
}
