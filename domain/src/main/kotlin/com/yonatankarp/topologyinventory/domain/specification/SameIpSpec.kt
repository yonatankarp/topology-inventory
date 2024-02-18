package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.Equipment
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class SameIpSpec(
    private val equipment: Equipment,
) : AbstractSpecification<Equipment>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(anyEquipment: Equipment) = equipment.ip != anyEquipment.ip

    override fun check(t: Equipment) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("It's not possible to attach routers with the same IP")
        }
    }
}
