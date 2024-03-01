package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.RemoveSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoveSwitchHttpAdapter(
    private val removeSwitchPort: RemoveSwitchPort,
    private val getRouterPort: GetRouterPort,
    private val addRouterPort: AddRouterPort,
) {
    /**
     * POST /switch/remove
     */
    operator fun invoke(
        switchId: Id,
        edgeRouterId: Id,
    ): EdgeRouter {
        val edgeRouter =
            getRouterPort(edgeRouterId) as? EdgeRouter
                ?: throw IllegalArgumentException("Edge router with id $edgeRouterId does not exist")
        val networkSwitch =
            edgeRouter.switches[switchId]
                ?: throw IllegalArgumentException("Switch with Id $switchId does not exist")
        val router = removeSwitchPort(networkSwitch, edgeRouter)

        return addRouterPort.persist(router) as EdgeRouter
    }
}
