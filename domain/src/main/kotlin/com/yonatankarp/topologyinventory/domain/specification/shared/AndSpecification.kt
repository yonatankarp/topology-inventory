package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException

class AndSpecification<T>(
    private val spec1: Specification<T>,
    private val spec2: Specification<T>,
) : AbstractSpecification<T>() {
    override fun isSatisfiedBy(t: T) = spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t)

    override fun check(t: T) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("At least one specification is not satisfied")
        }
    }
}
