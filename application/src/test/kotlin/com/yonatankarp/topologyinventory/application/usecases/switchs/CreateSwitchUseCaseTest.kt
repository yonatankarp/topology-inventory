package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class CreateSwitchUseCaseTest {
    @Test
    fun `invoke should create a switch with given parameters`() {
        // Given
        val vendor = Vendor.CISCO
        val model = Model.XYZ0001
        val ip = IPFixture.ipv4
        val location = mockk<Location>()
        val switchType = SwitchType.LAYER3
        val routerId = Id.withId("958a4b68-98f3-4d91-bcd7-1915c2faa836")
        val createSwitchUseCase = CreateSwitchUseCase()

        // When
        val result = createSwitchUseCase(vendor, model, ip, location, switchType, routerId)

        // Then
        assertEquals(vendor, result.vendor)
        assertEquals(model, result.model)
        assertEquals(ip, result.ip)
        assertEquals(location, result.location)
        assertEquals(switchType, result.switchType)
    }
}
