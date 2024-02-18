package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.FalseSpecification
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.TrueSpecification
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class AndSpecificationTest {

    @Test
    fun `isSatisfiedBy returns true when both spec1 and spec2 are satisfied`() {
        // Given
        val spec1 = TrueSpecification<String>()
        val spec2 = TrueSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy("value")

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when spec1 is satisfied but spec2 is not satisfied`() {
        // Given
        val spec1 = TrueSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy("value")

        // Then
        assertFalse(result)
    }

    @Test
    fun `isSatisfiedBy returns false when spec1 is not satisfied but spec2 is satisfied`() {
        // Given
        val spec1 = FalseSpecification<String>()
        val spec2 = TrueSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy("value")

        // Then
        assertFalse(result)
    }

    @Test
    fun `isSatisfiedBy returns false when both spec1 and spec2 are not satisfied`() {
        // Given
        val spec1 = FalseSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy("value")

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when at least one specification is not satisfied`() {
        // Given
        val spec1 = TrueSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When, Then
        assertThrows(GenericSpecificationException::class.java) {
            andSpecification.check("value")
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when both specifications are satisfied`() {
        // Given
        val spec1 = TrueSpecification<String>()
        val spec2 = TrueSpecification<String>()
        val andSpecification = AndSpecification(spec1, spec2)

        // When, Then
        assertDoesNotThrow {
            andSpecification.check("value")
        }
    }

}
