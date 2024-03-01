package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.valueobject.Id

class GetRouterHttpAdapter(
    private val getRouterPort: GetRouterPort,
) {
    /**
     * GET /router/retrieve/{id}
     */
    operator fun invoke(id: Id) = getRouterPort(id)
}
