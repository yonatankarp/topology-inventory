package com.yonatankarp.topologyinventory.application.usecases.switch

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.IP
import com.yonatankarp.topologyinventory.domain.valueobject.Location
import com.yonatankarp.topologyinventory.domain.valueobject.SwitchType
import com.yonatankarp.topologyinventory.domain.valueobject.Vendor

interface SwitchCreateUseCase {
    operator fun invoke(
        vendor: Vendor,
        module: Module,
        ip: IP,
        location: Location,
        switchType: SwitchType,
    ): Switch
}
