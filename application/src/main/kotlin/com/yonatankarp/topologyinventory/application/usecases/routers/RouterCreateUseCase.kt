package com.yonatankarp.topologyinventory.application.usecases.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.RouterCreateInputPort
import com.yonatankarp.topologyinventory.application.ports.output.RouterCreateOutputPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.entity.factory.RouterFactory
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class RouterCreateUseCase(
    private val routerCreateOutputPort: RouterCreateOutputPort,
) : RouterCreateInputPort {
    override fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        routerType: RouterType,
    ): Router = RouterFactory.getRouter(vendor, model, ip, location, routerType)
}
