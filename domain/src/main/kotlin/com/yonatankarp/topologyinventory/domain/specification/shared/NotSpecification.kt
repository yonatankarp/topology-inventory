package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException

class NotSpecification<T>(private val spec: Specification<T>) : AbstractSpecification<T>() {
    override fun isSatisfiedBy(t: T) = !spec.isSatisfiedBy(t)

    override fun check(t: T) {
        if (!isSatisfiedBy(t)) {
            throw GenericSpecificationException("Specification should not be satisfied")
        }
    }
}
