package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Equipment
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification

class SameCountrySpec(
    private val equipment: Equipment,
) : AbstractSpecification<Equipment>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(anyEquipment: Equipment) =
        if (anyEquipment is CoreRouter) {
            true
        } else {
            equipment.location.country == anyEquipment.location.country
        }

    override fun check(t: Equipment) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("The equipments should be in the same country")
        }
    }
}
