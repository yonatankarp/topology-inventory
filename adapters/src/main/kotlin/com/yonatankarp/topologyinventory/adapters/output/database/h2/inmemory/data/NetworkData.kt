package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "networks")
@MappedSuperclass
data class NetworkData(
    @Id
    @Column(name = "network_id")
    var id: Int = 0,
    @Column(name = "switch_id")
    var switchId: UUID? = null,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "network_address"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "network_protocol"),
        ),
    )
    var ip: IPData? = null,
    @Column(name = "network_name")
    var name: String? = null,
    @Column(name = "network_cidr")
    var cidr: Int? = null,
) : Serializable
