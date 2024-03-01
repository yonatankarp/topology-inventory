package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import org.springframework.web.bind.annotation.RestController

@RestController
class AddRouterHttpAdapter(
    private val getRouterPort: GetRouterPort,
    private val addRouterPort: AddRouterPort,
) {
    /**
     * POST /router/add
     */
    operator fun invoke(
        routerId: Id,
        coreRouterId: Id,
    ): Router {
        val router =
            getRouterPort(routerId)
                ?: throw IllegalArgumentException("Router id $routerId does not exist.")
        val coreRouter =
            getRouterPort(coreRouterId) as? CoreRouter
                ?: throw IllegalArgumentException("Core router  id $coreRouterId does not exist")
        return addRouterPort(router, coreRouter)
    }
}
