package com.yonatankarp.topologyinventory.application.usecases.switch

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

interface SwitchRemoveUseCase {
    fun removeFromEdgeRouter(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter
}
