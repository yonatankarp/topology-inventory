package com.yonatankarp.topologyinventory.domain.valueobject

data class IP(val ipAddress: String) {
    val protocol =
        if (ipAddress.length <= 15) {
            Protocol.IPv4
        } else {
            Protocol.IPv6
        }

    companion object {
        fun fromAddress(ipAddress: String) = IP(ipAddress)
    }
}
