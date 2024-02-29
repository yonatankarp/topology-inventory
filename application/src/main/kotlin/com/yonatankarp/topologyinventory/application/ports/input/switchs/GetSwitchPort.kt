package com.yonatankarp.topologyinventory.application.ports.input.switchs

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id

fun interface GetSwitchPort {
    operator fun invoke(id: Id): Switch?
}
