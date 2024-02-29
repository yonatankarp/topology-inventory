package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.LocationData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared.LocationMapper.toDomain
import com.yonatankarp.topologyinventory.domain.valueobject.Address
import com.yonatankarp.topologyinventory.domain.valueobject.City
import com.yonatankarp.topologyinventory.domain.valueobject.Country
import com.yonatankarp.topologyinventory.domain.valueobject.Latitude
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Longitude
import com.yonatankarp.topologyinventory.domain.valueobject.State
import com.yonatankarp.topologyinventory.domain.valueobject.ZipCode
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class LocationMapperTest {
    @Test
    fun `toDomain should map LocationData to Location`() {
        // Given
        val locationData =
            LocationData(
                address = "123 Main St",
                city = "Springfield",
                state = "IL",
                zipcode = 12345u,
                country = "USA",
                latitude = 40.7128f,
                longitude = -74.0060f,
            )

        // When
        val result = locationData.toDomain()

        // Then
        assertEquals("123 Main St", result.address.value)
        assertEquals("Springfield", result.city.value)
        assertEquals("IL", result.state.value)
        assertEquals(12345u, result.zipCode.value)
        assertEquals("USA", result.country.value)
        assertEquals(40.7128f, result.latitude.value)
        assertEquals(-74.0060f, result.longitude.value)
    }

    @Test
    fun `toData should map Location to LocationData`() {
        // Given
        val location =
            Location(
                address = Address("123 Main St"),
                city = City("Springfield"),
                state = State("IL"),
                zipCode = ZipCode(12345u),
                country = Country("USA"),
                latitude = Latitude(40.7128f),
                longitude = Longitude(-74.0060f),
            )

        // When
        val result = location.toData()

        // Then
        assertEquals("123 Main St", result.address)
        assertEquals("Springfield", result.city)
        assertEquals("IL", result.state)
        assertEquals(12345u, result.zipcode)
        assertEquals("USA", result.country)
        assertEquals(40.7128f, result.latitude)
        assertEquals(-74.0060f, result.longitude)
    }
}
