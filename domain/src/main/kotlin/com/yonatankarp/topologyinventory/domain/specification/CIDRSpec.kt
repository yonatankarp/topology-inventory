package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.AbstractSpecification
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR

class CIDRSpec : AbstractSpecification<CIDR>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(cidr: CIDR) = cidr.value >= MINIMUM_ALLOWED_CIDR

    @Throws(GenericSpecificationException::class)
    override fun check(t: CIDR) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("CIDR is below $MINIMUM_ALLOWED_CIDR")
        }
    }

    companion object {
        private const val MINIMUM_ALLOWED_CIDR = 8u
    }
}
