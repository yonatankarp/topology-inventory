package com.yonatankarp.topologyinventory.application.ports.output.switchs

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id

fun interface RetrieveSwitchPort {
    operator fun invoke(id: Id): Switch?
}
