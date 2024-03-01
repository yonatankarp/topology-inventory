package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.application.ports.output.switchs.RetrieveSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id

class GetSwitchUseCase(
    private val retrieveSwitchPort: RetrieveSwitchPort,
) : GetSwitchPort {
    override fun invoke(id: Id): Switch? = retrieveSwitchPort(id)
}
