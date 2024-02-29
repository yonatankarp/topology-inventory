package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.ModelData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchTypeData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.VendorData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.NetworkMapper.fromData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.NetworkMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toDomain
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

object SwitchMapper {
    fun SwitchData.toDomain() =
        Switch(
            switchId = Id(switchId!!),
            routerId = Id(routerId!!),
            vendor = Vendor.valueOf(switchVendor!!.name),
            model = Model.valueOf(switchModel!!.name),
            ip = IP(ip!!.address),
            location = switchLocation!!.toDomain(),
            switchType = SwitchType.valueOf(switchType!!.name),
            switchNetworks = networks!!.fromData(),
        )

    fun Switch.toData() =
        SwitchData(
            switchId = id.value,
            routerId = routerId.value,
            switchVendor = VendorData.valueOf(vendor.name),
            switchModel = ModelData.valueOf(model.name),
            ip = IPData(ip.ipAddress),
            switchLocation = location.toData(),
            switchType = SwitchTypeData.valueOf(switchType.name),
            networks = switchNetworks.toData(),
        )

    fun List<SwitchData>.fromData(): MutableMap<Id, Switch> =
        associate { Id.withId(it.switchId.toString()) to it.toDomain() }
            .toMutableMap()

    fun Map<Id, Switch>.toData(): List<SwitchData> = values.map { it.toData() }.toList()
}
