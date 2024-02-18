package com.yonatankarp.topologyinventory.domain.valueobject

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class LocationTest {
    @Test
    fun `location properties are correctly initialized`() {
        // Given
        val address = Address("123 Main St")
        val city = City("Springfield")
        val state = State("IL")
        val zipCode = ZipCode(12345)
        val country = Country("USA")
        val latitude = Latitude(40.7128f)
        val longitude = Longitude(-74.0060f)

        // When
        val location = Location(address, city, state, zipCode, country, latitude, longitude)

        // Then
        assertEquals(address, location.address)
        assertEquals(city, location.city)
        assertEquals(state, location.state)
        assertEquals(zipCode, location.zipCode)
        assertEquals(country, location.country)
        assertEquals(latitude, location.latitude)
        assertEquals(longitude, location.longitude)
    }
}
