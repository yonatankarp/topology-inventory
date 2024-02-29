package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.LocationDataFixture
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.ModelData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchTypeData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.VendorData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toDomain
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class SwitchMapperTest {
    @Test
    fun `toDomain should map SwitchData to Switch`() {
        // Given
        val switchId = Id.withoutId()
        val routerId = Id.withoutId()
        val switchData =
            SwitchData(
                switchId = switchId.value,
                routerId = routerId.value,
                switchVendor = VendorData.CISCO,
                switchModel = ModelData.XYZ0001,
                ip = IPData(IPFixture.IPV4),
                switchLocation = LocationDataFixture.locationData,
                switchType = SwitchTypeData.LAYER3,
                networks = emptyList(),
            )

        // When
        val result = switchData.toDomain()

        // Then
        assertEquals(switchId, result.id)
        assertEquals(routerId, result.routerId)
        assertEquals(Vendor.CISCO, result.vendor)
        assertEquals(Model.XYZ0001, result.model)
        assertEquals(IPFixture.ipv4, result.ip)
        assertEquals(SwitchType.LAYER3, result.switchType)
        assertTrue(result.switchNetworks.isEmpty())
    }

    @Test
    fun `toData should map Switch to SwitchData`() {
        // Given
        val switchId = Id.withoutId()
        val routerId = Id.withoutId()
        val switch =
            Switch(
                switchId = switchId,
                routerId = routerId,
                vendor = Vendor.CISCO,
                model = Model.XYZ0001,
                ip = IPFixture.ipv4,
                location = LocationFixture.createLocation("DE"),
                switchType = SwitchType.LAYER3,
                switchNetworks = mutableListOf(),
            )

        // When
        val result = switch.toData()

        // Then
        assertEquals(switchId.value, result.switchId)
        assertEquals(routerId.value, result.routerId)
        assertEquals(VendorData.CISCO, result.switchVendor)
        assertEquals(ModelData.XYZ0001, result.switchModel)
        assertEquals(IPFixture.ipv4.ipAddress, result.ip!!.address)
        assertEquals(SwitchTypeData.LAYER3, result.switchType)
    }
}
