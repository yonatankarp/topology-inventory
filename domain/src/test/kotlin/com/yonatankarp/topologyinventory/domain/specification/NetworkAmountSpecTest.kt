package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NetworkAmountSpecTest {
    @Test
    fun `switch with less than or equal to maximum allowed networks satisfies specification`() {
        // Given
        val networkAmountSpec = NetworkAmountSpec()
        val switch = SwitchFixture.emptySwitch

        // When
        val result = networkAmountSpec.isSatisfiedBy(switch)

        // Then
        assertTrue(result)
        assertDoesNotThrow { networkAmountSpec.check(switch) }
    }
}
