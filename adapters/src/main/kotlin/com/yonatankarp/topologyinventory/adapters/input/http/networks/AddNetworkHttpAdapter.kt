package com.yonatankarp.topologyinventory.adapters.input.http.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.AddNetworkPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.Network
import org.springframework.web.bind.annotation.RestController

@RestController
class AddNetworkHttpAdapter(
    private val getSwitchPort: GetSwitchPort,
    private val addNetworkPort: AddNetworkPort,
) {
    /**
     * POST /network/add
     */
    operator fun invoke(
        network: Network,
        switchId: Id,
    ): Switch {
        val networkSwitch =
            getSwitchPort(switchId)
                ?: throw IllegalArgumentException("Switch with id $switchId does not exist")
        return addNetworkPort(network, networkSwitch)
    }
}
