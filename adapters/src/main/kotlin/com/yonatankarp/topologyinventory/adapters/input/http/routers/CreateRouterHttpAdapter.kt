package com.yonatankarp.topologyinventory.adapters.input.http.routers

import com.yonatankarp.topologyinventory.application.ports.input.routers.CreateRouterPort
import com.yonatankarp.topologyinventory.application.ports.output.routers.PersistRouterPort
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateRouterHttpAdapter(
    private val createRouterPort: CreateRouterPort,
    private val persistRouterPort: PersistRouterPort,
) {
    /**
     * POST /router/create
     */
    operator fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        routerType: RouterType,
    ): Router {
        val router =
            createRouterPort(
                vendor,
                model,
                ip,
                location,
                routerType,
            )

        return persistRouterPort(router)
    }
}
