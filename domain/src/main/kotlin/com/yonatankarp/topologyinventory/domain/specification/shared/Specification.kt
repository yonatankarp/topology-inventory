package com.yonatankarp.topologyinventory.domain.specification.shared

interface Specification<T> {
    fun isSatisfiedBy(t: T): Boolean

    infix fun and(other: Specification<T>): Specification<T>

    infix fun or(other: Specification<T>): Specification<T>

    fun not(): Specification<T>
}
