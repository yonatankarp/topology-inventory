package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.specification.CIDRSpec
import com.yonatankarp.topologyinventory.domain.specification.NetworkAmountSpec
import com.yonatankarp.topologyinventory.domain.specification.NetworkAvailabilitySpec
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.Protocol
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class Switch(
    id: Id,
    vendor: Vendor,
    model: Model,
    ip: IP,
    location: Location,
    val switchType: SwitchType,
    private val _switchNetworks: MutableList<Network> = mutableListOf(),
) : Equipment(id, vendor, model, ip, location) {
    val switchNetworks: List<Network>
        get() = _switchNetworks

    fun addNetworkToSwitch(network: Network): Boolean {
        val availabilitySpec = NetworkAvailabilitySpec(network)
        val cidrSpec = CIDRSpec()
        val amountSpec = NetworkAmountSpec()

        cidrSpec.check(network.cidr)
        availabilitySpec.check(this)
        amountSpec.check(this)

        return _switchNetworks.add(network)
    }

    fun removeNetworkFromSwitch(network: Network): Boolean = _switchNetworks.remove(network)

    companion object {
        fun getNetworkProtocolPredicate(protocol: Protocol): (Network) -> Boolean =
            {
                it.address.protocol == protocol
            }

        fun getSwitchTypePredicate(switchType: SwitchType): (Switch) -> Boolean =
            {
                it.switchType == switchType
            }
    }
}
