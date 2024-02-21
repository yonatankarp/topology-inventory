package com.yonatankarp.topologyinventory.application.usecases.router

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

interface RouterCreateUseCase {
    operator fun invoke(
        vendor: Vendor,
        module: Module,
        ip: IP,
        location: Location,
        routerType: RouterType,
    ): Router
}
