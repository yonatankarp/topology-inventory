package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.routers.AddRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.routers.GetRouterPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.AddSwitchPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.CreateSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class CreateSwitchHttpAdapter(
    private val createSwitchPort: CreateSwitchPort,
    private val addSwitchPort: AddSwitchPort,
    private val getRouterPort: GetRouterPort,
    private val addRouterPort: AddRouterPort,
) {
    /**
     * POST /switch/create
     */
    operator fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        switchType: SwitchType,
        routerId: Id,
    ): EdgeRouter {
        val newSwitch =
            createSwitchPort(vendor, model, ip, location, switchType, routerId)
        val edgeRouter = getRouterPort(routerId)

        if (edgeRouter?.routerType != RouterType.EDGE) {
            throw UnsupportedOperationException("Please inform the id of the edge router to ad a switch")
        }

        val router = addSwitchPort(newSwitch, edgeRouter as EdgeRouter)

        return addRouterPort.persist(router) as EdgeRouter
    }
}
