package com.yonatankarp.topologyinventory.domain.specification

import com.yonatankarp.topologyinventory.domain.exception.GenericSpecificationException
import com.yonatankarp.topologyinventory.domain.valueobject.CIDR
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class CIDRSpecTest {
    @Test
    fun `CIDR value above minimum allowed`() {
        // Given
        val cidrSpec = CIDRSpec()
        val cidr = CIDR(10u)

        // When
        val result = cidrSpec.isSatisfiedBy(cidr)

        // Then
        assertTrue(result)
        assertDoesNotThrow { cidrSpec.check(cidr) }
    }

    @Test
    fun `CIDR value below minimum allowed`() {
        // Given
        val cidrSpec = CIDRSpec()
        val cidr = CIDR(5u)

        // When
        val result = cidrSpec.isSatisfiedBy(cidr)

        // Then
        assertFalse(result)
        assertThrows(GenericSpecificationException::class.java) {
            cidrSpec.check(cidr)
        }
    }

    @Test
    fun `CIDR value equals minimum allowed`() {
        // Given
        val cidrSpec = CIDRSpec()
        val cidr = CIDR(8u)

        // When
        val result = cidrSpec.isSatisfiedBy(cidr)

        // Then
        assertTrue(result)
        assertDoesNotThrow { cidrSpec.check(cidr) }
    }
}
