package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

object SwitchFixture {
    val emptySwitch: Switch
        get() =
            createNetworkSwitch(
                LocationFixture.createLocation("US"),
                emptyList(),
            )

    fun createSwitch(
        address: String,
        cidr: UInt,
        location: Location,
    ): Switch {
        val newNetwork = NetworkFixture.createNetwork(address, cidr)
        val networks = NetworkFixture.createNetworks(newNetwork)
        val networkSwitch = createNetworkSwitch(location, networks)
        return networkSwitch
    }

    private fun createNetworkSwitch(
        location: Location,
        networks: List<Network>,
    ) = Switch(
        Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490"),
        Vendor.CISCO,
        Model.XYZ0004,
        IP.fromAddress("20.0.0.100"),
        location,
        SwitchType.LAYER3,
        networks.toMutableList(),
    )
}
