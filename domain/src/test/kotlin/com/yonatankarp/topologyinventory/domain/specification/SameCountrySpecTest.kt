package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SameCountrySpecTest {
    @Test
    fun `isSatisfiedBy returns true when both equipments are CoreRouters`() {
        // Given
        val equipment1 = RouterFixture.coreRouter
        val equipment2 =
            RouterFixture.createCoreRouter(
                location = LocationFixture.createLocation("IL"),
                address = IPFixture.IPV6,
            )
        val sameCountrySpec = SameCountrySpec(equipment1)

        // When
        val result = sameCountrySpec.isSatisfiedBy(equipment2)

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns true when equipments are in the same country`() {
        // Given
        val equipment1 = RouterFixture.coreRouter
        val equipment2 =
            RouterFixture.createCoreRouter(
                location = LocationFixture.createLocation("US"),
                address = IPFixture.IPV6,
            )
        val sameCountrySpec = SameCountrySpec(equipment1)

        // When
        val result = sameCountrySpec.isSatisfiedBy(equipment2)

        // Then
        assertTrue(result)
    }

    @Test
    fun `isSatisfiedBy returns false when equipments are in different countries`() {
        // Given
        val equipment1 = RouterFixture.edgeRouter
        val equipment2 =
            RouterFixture.createEdgeRouter(
                location = LocationFixture.createLocation("NL"),
                address = IPFixture.IPV6,
            )
        val sameCountrySpec = SameCountrySpec(equipment1)

        // When
        val result = sameCountrySpec.isSatisfiedBy(equipment2)

        // Then
        assertFalse(result)
    }

    @Test
    fun `check throws GenericSpecificationException when equipments are in different countries`() {
        // Given
        val equipment1 = RouterFixture.edgeRouter
        val equipment2 =
            RouterFixture.createEdgeRouter(
                location = LocationFixture.createLocation("NL"),
                address = IPFixture.IPV6,
            )
        val sameCountrySpec = SameCountrySpec(equipment1)

        // When, Then
        assertThrows(GenericSpecificationException::class.java) {
            sameCountrySpec.check(equipment2)
        }
    }

    @Test
    fun `check does not throw GenericSpecificationException when equipments are in the same country`() {
        // Given
        val equipment1 = RouterFixture.edgeRouter
        val equipment2 = RouterFixture.edgeRouter
        val sameCountrySpec = SameCountrySpec(equipment1)

        // Then
        assertDoesNotThrow {
            // When
            sameCountrySpec.check(equipment2)
        }
    }
}
