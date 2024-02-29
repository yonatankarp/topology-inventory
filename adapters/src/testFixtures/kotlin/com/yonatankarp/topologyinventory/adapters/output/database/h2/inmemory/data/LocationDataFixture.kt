package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data

object LocationDataFixture {
    val locationData: LocationData
        get() =
            LocationData(
                address = "my address",
                city = "my city",
                state = "my state",
                zipcode = 12345u,
                country = "my country",
                latitude = 10f,
                longitude = -10f,
            )
}
