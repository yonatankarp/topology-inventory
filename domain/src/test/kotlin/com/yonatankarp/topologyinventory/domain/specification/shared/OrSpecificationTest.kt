package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.FalseSpecification
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.TrueSpecification
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class OrSpecificationTest {
    @Test
    fun `isSatisfiedBy returns true when either spec1 or spec2 is satisfied`() {
        // Given
        val spec1 = TrueSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy("value")

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when neither spec1 nor spec2 is satisfied`() {
        // Given
        val spec1 = FalseSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy("value")

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when neither spec1 nor spec2 is satisfied`() {
        // Given
        val spec1 = FalseSpecification<String>()
        val spec2 = FalseSpecification<String>()
        val orSpecification = OrSpecification(spec1, spec2)

        // Then
        assertThrows(GenericSpecificationException::class.java) {
            // When
            orSpecification.check("value")
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when either spec1 or spec2 is satisfied`() {
        // Given
        val spec1 = FalseSpecification<String>()
        val spec2 = TrueSpecification<String>()
        val orSpecification = OrSpecification(spec1, spec2)

        // When, Then
        assertDoesNotThrow {
            orSpecification.check("value")
        }
    }
}
