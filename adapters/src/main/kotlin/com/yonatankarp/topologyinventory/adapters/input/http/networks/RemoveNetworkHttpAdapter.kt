package com.yonatankarp.topologyinventory.adapters.input.http.networks

import com.yonatankarp.topologyinventory.application.ports.input.networks.RemoveNetworkPort
import com.yonatankarp.topologyinventory.application.ports.input.switchs.GetSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import com.yonatankarp.topologyinventory.domain.valueobject.NetworkName
import org.springframework.web.bind.annotation.RestController

@RestController
class RemoveNetworkHttpAdapter(
    private val getSwitchPort: GetSwitchPort,
    private val removeNetworkPort: RemoveNetworkPort,
) {
    /**
     * POST /network/remove
     */
    operator fun invoke(
        networkName: NetworkName,
        switchId: Id,
    ): Switch? {
        val networkSwitch =
            getSwitchPort(switchId)
                ?: throw IllegalArgumentException("Switch with id $switchId does not exist")
        return removeNetworkPort(networkName, networkSwitch)
    }
}
