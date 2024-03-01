package com.yonatankarp.topologyinventory.adapters.input.http.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id

class GetSwitchHttpAdapter(
    private val getSwitchPort: GetSwitchPort,
) {
    /**
     * GET /switch/retrieve/{id}
     */
    operator fun invoke(switchId: Id): Switch? = getSwitchPort(switchId)
}
