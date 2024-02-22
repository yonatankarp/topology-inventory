package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class SwitchCreateUseCaseTest {
    @Test
    fun `invoke should create a switch with given parameters`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val switchType = SwitchType.LAYER3
        val switchCreateUseCase = SwitchCreateUseCase()

        // When
        val result = switchCreateUseCase(vendor, model, ip, location, switchType)

        // Then
        assertEquals(vendor, result.vendor)
        assertEquals(model, result.model)
        assertEquals(ip, result.ip)
        assertEquals(location, result.location)
        assertEquals(switchType, result.switchType)
    }
}
