package com.yonatankarp.topologyinventory.domain.valueobject

import java.util.UUID

@JvmInline
value class Id(val value: UUID) {
    companion object {
        fun withId(id: String) = Id(UUID.fromString(id))

        fun withId(id: UUID) = Id(id)

        fun withoutId() = Id(UUID.randomUUID())
    }
}
