package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.application.ports.output.routers.GetRouterPort as GetRouterOutputPort

class GetRouterUseCase(
    private val getRouterPort: GetRouterOutputPort,
) : GetRouterPort {
    override fun invoke(id: Id): Router? = getRouterPort(id)
}
