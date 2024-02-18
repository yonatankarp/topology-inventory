package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import kotlin.jvm.Throws

abstract class AbstractSpecification<T> : Specification<T> {
    abstract override fun isSatisfiedBy(t: T): Boolean

    @Throws(GenericSpecificationException::class)
    abstract fun check(t: T)

    override fun and(other: Specification<T>): Specification<T> = AndSpecification(this, other)

    override fun or(other: Specification<T>): Specification<T> = OrSpecification(this, other)

    override fun not(): Specification<T> = NotSpecification(this)
}
