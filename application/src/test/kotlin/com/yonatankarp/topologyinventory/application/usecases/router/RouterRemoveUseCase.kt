package com.yonatankarp.topologyinventory.application.usecases.router

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

interface RouterRemoveUseCase {
    operator fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): Router
}
