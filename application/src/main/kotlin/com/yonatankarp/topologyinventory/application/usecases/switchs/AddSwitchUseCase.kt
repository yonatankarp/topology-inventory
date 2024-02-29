package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.AddSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

class AddSwitchUseCase : AddSwitchPort {
    override fun invoke(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter {
        edgeRouter.addSwitch(networkSwitch)
        return edgeRouter
    }
}
