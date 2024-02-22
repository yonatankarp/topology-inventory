package com.yonatankarp.topologyinventory.application.ports.input.switchs

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

interface SwitchRemoveInputPort {
    fun removeFromEdgeRouter(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter
}
