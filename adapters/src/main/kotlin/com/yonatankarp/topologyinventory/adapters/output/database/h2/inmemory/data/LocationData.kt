package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Table

@Entity
@Table(name = "location")
@MappedSuperclass
data class LocationData(
    @Id
    @Column(name = "location_id")
    var locationId: Int = 0,
    @Column(name = "address")
    var address: String? = null,
    @Column(name = "city")
    var city: String? = null,
    @Column(name = "state")
    var state: String? = null,
    @Column(name = "zipcode")
    var zipcode: UInt = 0u,
    @Column(name = "country")
    var country: String? = null,
    @Column(name = "latitude")
    var latitude: Float = 0f,
    @Column(name = "longitude")
    var longitude: Float = 0f,
)
