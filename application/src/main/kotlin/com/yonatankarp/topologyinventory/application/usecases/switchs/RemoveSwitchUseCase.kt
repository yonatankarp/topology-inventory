package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.RemoveSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

class RemoveSwitchUseCase : RemoveSwitchPort {
    override fun invoke(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter {
        edgeRouter.removeSwitch(networkSwitch)
        return edgeRouter
    }
}
