package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.RemoveRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

class RemoveRouterHttpAdapter(
    private val getRouterPort: GetRouterPort,
    private val removeRouterPort: RemoveRouterPort,
) {
    /**
     * GET /router/remove/{id}
     */
    operator fun invoke(id: Id) = removeRouterPort(id)

    /**
     * POST /router/remove
     */
    fun fromCoreRouter(
        routerId: Id,
        coreRouterId: Id,
    ): Router? {
        val router =
            getRouterPort(routerId)
                ?: throw IllegalArgumentException("Router id $routerId does not exist.")
        val coreRouter =
            getRouterPort(coreRouterId) as? CoreRouter
                ?: throw IllegalArgumentException("Core router  id $coreRouterId does not exist")

        return removeRouterPort.fromCoreRouter(router, coreRouter)
    }
}
