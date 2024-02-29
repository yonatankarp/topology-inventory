package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.specification.EmptyNetworkSpec
import com.yonatankarp.topologyinventory.domain.specification.SameCountrySpec
import com.yonatankarp.topologyinventory.domain.specification.SameIpSpec
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class EdgeRouter(
    id: Id,
    vendor: Vendor,
    model: Model,
    ip: IP,
    location: Location,
    routerType: RouterType,
    var switches: MutableMap<Id, Switch> = mutableMapOf(),
) : Router(id, vendor, model, ip, location, routerType) {
    fun addSwitch(anySwitch: Switch) {
        val sameCountryRouterSpec = SameCountrySpec(this)
        val sameIpSpec = SameIpSpec(this)

        sameCountryRouterSpec.check(anySwitch)
        sameIpSpec.check(anySwitch)

        switches[anySwitch.id] = anySwitch
    }

    fun removeSwitch(anySwitch: Switch): Switch? {
        val emptyNetworkSpec = EmptyNetworkSpec()
        emptyNetworkSpec.check(anySwitch)
        return switches.remove(anySwitch.id)
    }
}
