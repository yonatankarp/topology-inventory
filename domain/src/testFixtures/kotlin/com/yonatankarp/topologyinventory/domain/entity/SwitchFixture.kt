package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
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

    val switches: List<Switch>
        get() =
            listOf(
                createSwitch(
                    IPFixture.IPV4,
                    12u,
                    LocationFixture.createLocation("US"),
                ),
                createSwitch(
                    IPFixture.IPV4,
                    12u,
                    LocationFixture.createLocation("GB"),
                ),
                createSwitch(
                    IPFixture.IPV6,
                    12u,
                    LocationFixture.createLocation("DE"),
                ),
            )

    val ipv4SwitchOnly: List<Switch>
        get() =
            listOf(
                createSwitch(
                    IPFixture.IPV4,
                    12u,
                    LocationFixture.createLocation("US"),
                ),
                createSwitch(
                    IPFixture.IPV4,
                    12u,
                    LocationFixture.createLocation("GB"),
                ),
            )

    val switchesMap: Map<Id, Switch>
        get() =
            mapOf(
                Id.withId("82e24dc0-170b-45e0-b70f-9f133f0a33db") to
                    createSwitch(
                        IPFixture.IPV4,
                        12u,
                        LocationFixture.createLocation("US"),
                    ),
                Id.withId("176f5148-54fc-4646-9ac2-0e9d99d7c6e8") to
                    createSwitch(
                        IPFixture.IPV4,
                        12u,
                        LocationFixture.createLocation("GB"),
                    ),
                Id.withId("78c3ed36-4ca7-4adf-a4c6-97e6ce280e7a") to
                    createSwitch(
                        IPFixture.IPV6,
                        12u,
                        LocationFixture.createLocation("DE"),
                    ),
            )
}
