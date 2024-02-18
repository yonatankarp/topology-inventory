package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

abstract class Router(
    id: Id,
    vendor: Vendor,
    model: Model,
    ip: IP,
    location: Location,
    val routerType: RouterType,
) : Equipment(id, vendor, model, ip, location) {
    companion object {
        fun getRouterTypePredicate(routerType: RouterType): (Equipment) -> Boolean = { it is Router && it.routerType == routerType }

        fun getModelPredicate(model: Model): (Equipment) -> Boolean = { it is Router && it.model == model }

        fun getCountryPredicate(location: Location): (Equipment) -> Boolean = { it is Router && it.location.country == location.country }
    }
}
