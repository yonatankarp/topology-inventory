package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "switches")
@MappedSuperclass
data class SwitchData(
    @Id
    @Column(name = "switch_id", columnDefinition = "uuid", updatable = false)
    var switchId: UUID? = null,
    @Column(name = "router_id")
    val routerId: UUID? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "switch_vendor")
    var switchVendor: VendorData? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "switch_model")
    var switchModel: ModelData? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "switch_type")
    var switchType: SwitchTypeData? = null,
    @OneToMany
    @JoinColumn(
        table = "networks",
        name = "switch_id",
        referencedColumnName = "switch_id",
    )
    var networks: List<NetworkData>? = null,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "switch_ip_address"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "switch_ip_protocol"),
        ),
    )
    var ip: IPData? = null,
    @ManyToOne
    @JoinColumn(name = "location_id")
    var switchLocation: LocationData? = null,
)
