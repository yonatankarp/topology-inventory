package com.yonatankarp.topologyinventory.application.usecases.switchs

import com.yonatankarp.topologyinventory.application.ports.input.switchs.SwitchRemoveInputPort
import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

class SwitchRemoveUseCase : SwitchRemoveInputPort {
    override fun removeFromEdgeRouter(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter {
        edgeRouter.removeSwitch(networkSwitch)
        return edgeRouter
    }
}
