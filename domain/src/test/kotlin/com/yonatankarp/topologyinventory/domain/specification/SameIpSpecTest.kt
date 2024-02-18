package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class SameIpSpecTest {

    @Test
    fun `isSatisfiedBy returns true when equipments have different IPs`() {
        // Given
        val equipment1 = RouterFixture.edgeRouter
        val equipment2 = RouterFixture.coreRouter
        val sameIpSpec = SameIpSpec(equipment1)

        // When
        val result = sameIpSpec.isSatisfiedBy(equipment2)

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when equipments have the same IP`() {
        // Given
        val equipment1 = RouterFixture.coreRouter
        val equipment2 = RouterFixture.coreRouter
        val sameIpSpec = SameIpSpec(equipment1)

        // When
        val result = sameIpSpec.isSatisfiedBy(equipment2)

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when equipments have the same IP`() {
        // Given
        val equipment1 = RouterFixture.coreRouter
        val equipment2 = RouterFixture.coreRouter
        val sameIpSpec = SameIpSpec(equipment1)

        // When, Then
        assertThrows(GenericSpecificationException::class.java) {
            sameIpSpec.check(equipment2)
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when equipments have different IPs`() {
        // Given
        val equipment1 = RouterFixture.coreRouter
        val equipment2 = RouterFixture.edgeRouter
        val sameIpSpec = SameIpSpec(equipment1)

        // Then
        assertDoesNotThrow {
            // When
            sameIpSpec.check(equipment2)
        }
    }
}
