package com.yonatankarp.topologyinventory.domain.valueobject

data class Network(
    val address: IP,
    val name: NetworkName,
    val cidr: CIDR,
) {
    init {
        require(cidr.value in 1u..32u) { "Invalid CIDR value" }
    }

    companion object {
        fun getNetworkProtocolPredicate(protocol: Protocol): (Network) -> Boolean = { it.address.protocol == protocol }

        fun getNetworkNetPredicate(name: NetworkName): (Network) -> Boolean = { it.name == name }
    }
}

@JvmInline
value class NetworkName(val value: String)

@JvmInline
value class CIDR(val value: UInt)
