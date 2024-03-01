package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import org.springframework.web.bind.annotation.RestController

@RestController
class GetRouterHttpAdapter(
    private val getRouterPort: GetRouterPort,
) {
    /**
     * GET /router/retrieve/{id}
     */
    operator fun invoke(id: Id) = getRouterPort(id)
}
