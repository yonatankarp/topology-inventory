package com.yonatankarp.topologyinventory.application.usecases.router

import com.yonatankarp.topologyinventory.domain.entity.CoreRouter
import com.yonatankarp.topologyinventory.domain.entity.Router

interface RouterAddUseCase {
    operator fun invoke(
        router: Router,
        coreRouter: CoreRouter,
    ): CoreRouter

    fun persist(router: Router): Router
}
