package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.SwitchCreateInputPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class SwitchCreateUseCase : SwitchCreateInputPort {
    override fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        switchType: SwitchType,
    ) = Switch(
        Id.withoutId(),
        vendor,
        model,
        ip,
        location,
        switchType,
    )
}
