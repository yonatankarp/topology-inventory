package com.yonatankarp.topologyinventory.application.usecases.router

import com.yonatankarp.topologyinventory.domain.entity.Router
import com.yonatankarp.topologyinventory.domain.valueobject.Id

interface RouterGetUseCase {
    operator fun invoke(id: Id): Router
}
