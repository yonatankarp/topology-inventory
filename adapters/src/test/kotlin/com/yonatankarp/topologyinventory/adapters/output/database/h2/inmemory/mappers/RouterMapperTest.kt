package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.IPData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.LocationDataFixture
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.ModelData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.RouterTypeData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.VendorData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.RouterMapper.toDomain
import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.valueobject.IPFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.LocationFixture
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.RouterType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RouterMapperTest {
    @Test
    fun `toDomain should map RouterData to Router`() {
        // Given
        val routerId = Id.withoutId()
        val routerData =
            RouterData(
                routerId = routerId.value,
                routerVendor = VendorData.CISCO,
                routerModel = ModelData.XYZ0001,
                ip = IPData(IPFixture.IPV4),
                routerLocation = LocationDataFixture.locationData,
                routerType = RouterTypeData.CORE,
                routers = emptyList(),
            )

        // When
        val result = routerData.toDomain()

        // Then
        assertEquals(routerId, result.id)
        assertEquals(Vendor.CISCO, result.vendor)
        assertEquals(Model.XYZ0001, result.model)
        assertEquals(IPFixture.ipv4, result.ip)
        assertEquals(RouterType.CORE, result.routerType)
    }

    @Test
    fun `toData should map Router to RouterData`() {
        // Given
        val routerId = Id.withoutId()
        val router =
            CoreRouter(
                id = routerId,
                vendor = Vendor.CISCO,
                model = Model.XYZ0001,
                ip = IPFixture.ipv4,
                location = LocationFixture.createLocation("US"),
                routerType = RouterType.CORE,
            )

        // When
        val result = router.toData()

        // Then
        assertEquals(routerId.value, result.routerId)
        assertEquals(VendorData.CISCO, result.routerVendor)
        assertEquals(ModelData.XYZ0001, result.routerModel)
        assertEquals(IPFixture.IPV4, result.ip!!.address)
        assertEquals(RouterTypeData.CORE, result.routerType)
    }
}
