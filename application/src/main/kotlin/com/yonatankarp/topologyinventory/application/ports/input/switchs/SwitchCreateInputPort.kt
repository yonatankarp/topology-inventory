package com.yonatankarp.topologyinventory.application.ports.input.switchs

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

interface SwitchCreateInputPort {
    operator fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        switchType: SwitchType,
    ): Switch
}
