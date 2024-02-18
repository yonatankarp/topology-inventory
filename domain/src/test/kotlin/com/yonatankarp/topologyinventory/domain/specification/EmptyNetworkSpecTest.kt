package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkFixture
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class EmptyNetworkSpecTest {
    @Test
    fun `switch with empty network list satisfies specification`() {
        // Given
        val emptyNetworkSpec = EmptyNetworkSpec()
        val switch = SwitchFixture.emptySwitch

        // When
        val result = emptyNetworkSpec.isSatisfiedBy(switch)

        // Then
        assertTrue(result)
        assertDoesNotThrow { emptyNetworkSpec.check(switch) }
    }

    @Test
    fun `switch with non-empty network list does not satisfy specification`() {
        // Given
        val emptyNetworkSpec = EmptyNetworkSpec()
        val switch =
            SwitchFixture.createSwitch(
                address = IPFixture.IPV4,
                cidr = NetworkFixture.cidr.value,
                location = LocationFixture.createLocation("IL"),
            )

        // When
        val result = emptyNetworkSpec.isSatisfiedBy(switch)

        // Then
        assertFalse(result)
        assertThrows(GenericSpecificationException::class.java) {
            emptyNetworkSpec.check(switch)
        }
    }
}
