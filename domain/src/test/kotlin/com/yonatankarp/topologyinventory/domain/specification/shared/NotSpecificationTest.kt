package com.yonatankarp.topologyinventory.domain.specification.shared

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.FalseSpecification
import com.yonatankarp.topologyinventory.domain.specification.shared.mock.TrueSpecification
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NotSpecificationTest {

    @Test
    fun `isSatisfiedBy returns true when the inner specification is not satisfied`() {
        // Given
        val innerSpec = FalseSpecification<String>()
        val notSpecification = NotSpecification(innerSpec)

        // When
        val result = notSpecification.isSatisfiedBy("value")

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when the inner specification is satisfied`() {
        // Given
        val innerSpec = TrueSpecification<String>()
        val notSpecification = NotSpecification(innerSpec)

        // When
        val result = notSpecification.isSatisfiedBy("value")

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when the inner specification is satisfied`() {
        // Given
        val innerSpec = TrueSpecification<String>()
        val notSpecification = NotSpecification(innerSpec)

        // When, Then
        assertThrows(GenericSpecificationException::class.java) {
            notSpecification.check("value")
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when the inner specification is not satisfied`() {
        // Given
        val innerSpec = FalseSpecification<String>()
        val notSpecification = NotSpecification(innerSpec)

        // When, Then
        assertDoesNotThrow {
            notSpecification.check("value")
        }
    }
}
