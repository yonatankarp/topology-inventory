package com.yonatankarp.topologyinventory.domain.valueobject

object NetworkFixture {
    val networkName = NetworkName("MyNetwork")
    val cidr = CIDR(24u)

    val network: Network
        get() =
            Network(
                address = IP(IPFixture.IPV4),
                name = networkName,
                cidr = cidr,
            )

    fun createNetwork(
        address: String,
        cidr: UInt,
    ) = Network(
        address = IP(address),
        name = NetworkName("NewNetwork"),
        cidr = CIDR(cidr),
    )

    fun createNetworks(network: Network) = listOf(network)
}
