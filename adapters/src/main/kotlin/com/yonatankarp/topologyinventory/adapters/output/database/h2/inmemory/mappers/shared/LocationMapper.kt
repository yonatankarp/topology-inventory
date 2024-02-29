package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.shared

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.LocationData
import com.yonatankarp.topologyinventory.domain.valueobject.Address
import com.yonatankarp.topologyinventory.domain.valueobject.City
import com.yonatankarp.topologyinventory.domain.valueobject.Country
import com.yonatankarp.topologyinventory.domain.valueobject.Latitude
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Longitude
import com.yonatankarp.topologyinventory.domain.valueobject.State
import com.yonatankarp.topologyinventory.domain.valueobject.ZipCode

object LocationMapper {
    fun LocationData.toDomain() =
        Location(
            address = Address(address!!),
            city = City(city!!),
            state = State(state!!),
            zipCode = ZipCode(zipcode),
            country = Country(country!!),
            latitude = Latitude(latitude),
            longitude = Longitude(longitude),
        )

    fun Location.toData() =
        LocationData(
            address = address.value,
            city = city.value,
            state = state.value,
            zipcode = zipCode.value,
            country = country.value,
            latitude = latitude.value,
            longitude = longitude.value,
        )
}
