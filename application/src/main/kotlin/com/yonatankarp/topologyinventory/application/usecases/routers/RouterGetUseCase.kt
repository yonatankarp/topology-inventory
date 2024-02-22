package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.RouterGetInputPort
import com.yonatankarp.topologyinventory.application.ports.output.RouterGetOutputPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

class RouterGetUseCase(
    private val routerGetOutputPort: RouterGetOutputPort,
) : RouterGetInputPort {
    override fun invoke(id: Id): Router = routerGetOutputPort.retrieveRouter(id)
}
