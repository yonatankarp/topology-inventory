package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class IPData(val address: String = "") {
    @Embedded
    @Enumerated(EnumType.STRING)
    val protocol =
        if (address.length <= 15) {
            ProtocolData.IPV4
        } else {
            ProtocolData.IPV6
        }
}
