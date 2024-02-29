package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.NetworkData
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName

object NetworkMapper {
    fun NetworkData.toDomain() =
        Network(
            address = IP(ip!!.address),
            name = NetworkName(name!!),
            cidr = CIDR(cidr!!.toUInt()),
        )

    fun Network.toData() =
        NetworkData(
            ip = IPData(address.ipAddress),
            name = name.value,
            cidr = cidr.value.toInt(),
        )

    fun List<NetworkData>.fromData(): MutableList<Network> = map { it.toDomain() }.toMutableList()

    fun List<Network>.toData(): List<NetworkData> = map { it.toData() }.toList()
}
