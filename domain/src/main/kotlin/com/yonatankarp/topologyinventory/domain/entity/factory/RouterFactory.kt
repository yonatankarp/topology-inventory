package com.yonatankarp.topologyinventory.domain.entity.factory

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType.CORE
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType.EDGE
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

object RouterFactory {
    fun getRouter(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        routerType: RouterType,
        id: Id? = null,
    ): Router =
        when (routerType) {
            CORE ->
                CoreRouter(
                    id = id ?: Id.withoutId(),
                    vendor = vendor,
                    model = model,
                    ip = ip,
                    location = location,
                    routerType = routerType,
                )

            EDGE ->
                EdgeRouter(
                    id = id ?: Id.withoutId(),
                    vendor = vendor,
                    model = model,
                    ip = ip,
                    location = location,
                    routerType = routerType,
                )
        }
}
