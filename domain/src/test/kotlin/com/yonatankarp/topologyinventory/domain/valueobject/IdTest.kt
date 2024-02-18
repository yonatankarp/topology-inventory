package com.yonatankarp.topologyinventory.domain.valueobject

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.UUID

class IdTest {
    @Test
    fun `given valid UUID string when withId then creates Id with correct UUID`() {
        // Given
        val uuid = UUID.randomUUID()

        // When
        val id = Id.withId(uuid.toString())

        // Then
        assertEquals(uuid, id.value)
    }

    @Test
    fun `given no arguments when withoutId then creates Id with random UUID`() {
        // When
        val id = Id.withoutId()

        // Then
        assertTrue(
            id.value.toString()
                .matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}".toRegex()),
        )
    }
}
