package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.entity.RouterFixture
import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture
import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class EmptySwitchSpecTest {
    @Test
    fun `edge router with empty list of switches satisfies specification`() {
        // Given
        val emptySwitchSpec = EmptySwitchSpec()
        val edgeRouter = RouterFixture.edgeRouter

        // When
        val result = emptySwitchSpec.isSatisfiedBy(edgeRouter)

        // Then
        assertTrue(result)
        assertDoesNotThrow { emptySwitchSpec.check(edgeRouter) }
    }

    @Test
    fun `edge router with non-empty list of switches does not satisfy specification`() {
        // Given
        val emptySwitchSpec = EmptySwitchSpec()
        val edgeRouter = RouterFixture.edgeRouter
        edgeRouter.addSwitch(SwitchFixture.emptySwitch)

        // When
        val result = emptySwitchSpec.isSatisfiedBy(edgeRouter)

        // Then
        assertFalse(result)
        assertThrows(GenericSpecificationException::class.java) { emptySwitchSpec.check(edgeRouter) }
    }
}
