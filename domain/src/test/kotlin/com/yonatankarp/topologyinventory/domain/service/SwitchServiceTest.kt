package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture.ipv4SwitchOnly
import com.yonatankarp.topologyinventory.domain.entity.SwitchFixture.switchesMap
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertNull

class SwitchServiceTest {
    @Test
    fun `filterAndRetrieveSwitch returns an empty list if no switches match the predicate`() {
        // Given
        val switches = ipv4SwitchOnly
        val predicate: (Switch) -> Boolean =
            { it.ip.ipAddress == IPFixture.IPV6 }

        // When
        val filteredSwitches =
            SwitchService.filterAndRetrieveSwitch(switches, predicate)

        // Then
        assertTrue(filteredSwitches.isEmpty())
    }

    @Test
    fun `findById returns the switch with the specified id`() {
        // Given
        val switches = switchesMap
        val idToFind = Id.withId("78c3ed36-4ca7-4adf-a4c6-97e6ce280e7a")

        // When
        val result = SwitchService.findById(switches, idToFind)

        // Then
        val expected = switches[idToFind]
        assertEquals(expected, result)
    }

    @Test
    fun `findById returns null if the switch with the specified id does not exist`() {
        // Given
        val switches = switchesMap
        val idToFind = Id.withId("c7d20e0e-0b54-4956-8fc7-d5b55894cbc3")

        // When
        val result = SwitchService.findById(switches, idToFind)

        // Then
        assertNull(result)
    }
}
