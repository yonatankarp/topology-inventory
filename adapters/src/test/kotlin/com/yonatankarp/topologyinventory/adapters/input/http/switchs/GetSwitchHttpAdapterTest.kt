package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetSwitchHttpAdapterTest {
    private val getSwitchPort = mockk<GetSwitchPort>()

    @BeforeEach
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `invoke should retrieve switch`() {
        // Given
        val switchId = Id.withoutId()
        val switch = mockk<Switch>()

        every { getSwitchPort(switchId) } returns switch

        val getSwitchHttpAdapter = GetSwitchHttpAdapter(getSwitchPort)

        // When
        val result = getSwitchHttpAdapter(switchId)

        // Then
        assertEquals(switch, result)
    }
}
