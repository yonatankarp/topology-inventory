package com.yonatankarp.topologyinventory.domain.valueobject

object LocationFixture {
    fun createLocation(country: String) =
        Location(
            Address("Test street"),
            City("Test city"),
            State("Test state"),
            ZipCode(12345u),
            Country(country),
            10f.latitude(),
            (-10f).longitude(),
        )
}
