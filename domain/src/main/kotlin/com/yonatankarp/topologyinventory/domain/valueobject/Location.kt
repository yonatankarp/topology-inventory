package com.yonatankarp.topologyinventory.domain.valueobject

data class Location(
    val address: Address,
    val city: City,
    val state: State,
    val zipCode: ZipCode,
    val country: Country,
    val latitude: Latitude,
    val longitude: Longitude,
)

@JvmInline
value class Address(val value: String)

@JvmInline
value class City(val value: String)

@JvmInline
value class State(val value: String)

@JvmInline
value class ZipCode(val value: UInt)

@JvmInline
value class Country(val value: String)

@JvmInline
value class Latitude(val value: Float)

@JvmInline
value class Longitude(val value: Float)

fun Float.latitude() = Latitude(this)

fun Float.longitude() = Longitude(this)
