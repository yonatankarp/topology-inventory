package com.yonatankarp.topologyinventory.domain.specification.shared.mock

import com.yonatankarp.topologyinventory.domain.specification.shared.Specification

class FalseSpecification<T> : Specification<T> {
    override fun isSatisfiedBy(t: T): Boolean = false
    override infix fun and(other: Specification<T>): Specification<T> = this
    override infix fun or(other: Specification<T>): Specification<T> = this
    override fun not(): Specification<T> = TrueSpecification()
}
