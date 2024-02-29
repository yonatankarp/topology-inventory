package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.ModelData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterTypeData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.VendorData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.fromData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toDomain
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.entity.factory.RouterFactory
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

object RouterMapper {
    fun RouterData.toDomain(): Router {
        val router =
            RouterFactory.getRouter(
                id = Id(routerId!!),
                vendor = Vendor.valueOf(routerVendor!!.name),
                model = Model.valueOf(routerModel!!.name),
                ip = IP(ip!!.address),
                location = routerLocation!!.toDomain(),
                routerType = RouterType.valueOf(routerType!!.name),
            )

        return if (routerType == RouterTypeData.CORE) {
            val coreRouter = router as CoreRouter
            coreRouter.routers = routers.fromData()
            coreRouter
        } else {
            val edgeRouter = router as EdgeRouter
            edgeRouter.switches = switches.fromData()
            edgeRouter
        }
    }

    fun Router.toData(): RouterData {
        val routerData =
            RouterData(
                routerId = id.value,
                routerVendor = VendorData.valueOf(vendor.name),
                routerModel = ModelData.valueOf(model.name),
                ip = IPData(ip.ipAddress),
                routerLocation = location.toData(),
                routerType = RouterTypeData.valueOf(routerType.name),
            )

        if (routerType == RouterType.CORE) {
            val coreRouter = this as CoreRouter
            routerData.routers = coreRouter.routers.toData()
        } else {
            val edgeRouter = this as EdgeRouter
            routerData.switches = edgeRouter.switches.toData()
        }

        return routerData
    }

    private fun List<RouterData>.fromData(): MutableMap<Id, Router> =
        associate { Id.withId(it.routerId.toString()) to it.toDomain() }
            .toMutableMap()

    private fun Map<Id, Router>.toData(): List<RouterData> = values.map { it.toData() }
}
