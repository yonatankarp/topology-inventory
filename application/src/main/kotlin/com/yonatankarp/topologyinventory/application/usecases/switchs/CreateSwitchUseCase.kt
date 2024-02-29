package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.CreateSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.Model
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

class CreateSwitchUseCase : CreateSwitchPort {
    override fun invoke(
        vendor: Vendor,
        model: Model,
        ip: IP,
        location: Location,
        switchType: SwitchType,
        routerId: Id,
    ) = Switch(
        Id.withoutId(),
        vendor,
        model,
        ip,
        location,
        switchType,
        routerId,
    )
}
