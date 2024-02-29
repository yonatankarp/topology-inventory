package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture.IPV4
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture.IPV6
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

object RouterFixture {
    val coreRouter: CoreRouter
        get() =
            CoreRouter(
                Id.withoutId(),
                Vendor.HP,
                Model.XYZ0001,
                IP(IPV4),
                LocationFixture.createLocation("US"),
                RouterType.CORE,
            )

    val edgeRouter: EdgeRouter
        get() =
            EdgeRouter(
                Id.withoutId(),
                Vendor.CISCO,
                Model.XYZ0002,
                IP(IPV6),
                LocationFixture.createLocation("US"),
                RouterType.EDGE,
            )

    fun createEdgeRouter(
        location: Location,
        address: String,
    ) = EdgeRouter(
        Id.withoutId(),
        Vendor.CISCO,
        Model.XYZ0002,
        IP(address),
        location,
        RouterType.EDGE,
    )

    fun createCoreRouter(
        location: Location,
        address: String,
    ) = CoreRouter(
        Id.withoutId(),
        Vendor.HP,
        Model.XYZ0001,
        IP(address),
        location,
        RouterType.CORE,
    )
}
