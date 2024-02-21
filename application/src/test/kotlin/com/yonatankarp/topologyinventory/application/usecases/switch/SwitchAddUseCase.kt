package com.yonatankarp.topologyinventory.application.usecases.switch

import com.yonatankarp.topologyinventory.domain.entity.EdgeRouter
import com.yonatankarp.topologyinventory.domain.entity.Switch

interface SwitchAddUseCase {
    operator fun invoke(
        networkSwitch: Switch,
        edgeRouter: EdgeRouter,
    ): EdgeRouter
}
