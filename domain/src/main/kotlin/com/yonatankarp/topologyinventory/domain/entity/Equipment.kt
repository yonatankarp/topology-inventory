package com.yonatankarp.topologyinventory.domain.entity

import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

abstract class Equipment(
    val id: Id,
    val vendor: Vendor,
    val model: Model,
    val ip: IP,
    val location: Location,
) {
    companion object {
        fun getVendorPredicate(vendor: Vendor): (Equipment) -> Boolean = { it.vendor == vendor }
    }
}
