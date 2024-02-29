package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.specification.CIDRSpec
import com.yonatankarp.topologyinventory.domain.specification.NetworkAmountSpec
import com.yonatankarp.topologyinventory.domain.specification.NetworkAvailabilitySpec
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class Switch(
    switchId: Id,
    vendor: Vendor,
    model: Model,
    ip: IP,
    location: Location,
    val switchType: SwitchType,
    val routerId: Id,
    var switchNetworks: MutableList<Network> = mutableListOf(),
) : Equipment(switchId, vendor, model, ip, location) {
    fun addNetworkToSwitch(network: Network): Boolean {
        val availabilitySpec = NetworkAvailabilitySpec(network)
        val cidrSpec = CIDRSpec()
        val amountSpec = NetworkAmountSpec()

        cidrSpec.check(network.cidr)
        availabilitySpec.check(this)
        amountSpec.check(this)

        return switchNetworks.add(network)
    }

    fun removeNetworksFromSwitch(networks: Collection<Network>): Boolean =
        networks.map { removeNetworkFromSwitch(it) }.reduce { a, b -> a && b }

    fun removeNetworkFromSwitch(network: Network): Boolean = switchNetworks.remove(network)

    companion object {
        fun getSwitchTypePredicate(switchType: SwitchType): (Switch) -> Boolean = { it.switchType == switchType }
    }
}
