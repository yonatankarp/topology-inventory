package com.yonatankarp.topologyinventory.application.ports.input.routers

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

fun interface RouterCreateInputPort {
    operator fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        routerType: RouterType,
    ): Router
}
