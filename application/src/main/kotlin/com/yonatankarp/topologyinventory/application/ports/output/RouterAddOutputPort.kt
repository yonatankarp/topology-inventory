package com.yonatankarp.topologyinventory.application.ports.output

import com.yonatankarp.topologyinventory.domain.entity.Router

interface RouterAddOutputPort {
    fun persistRouter(router: Router): Router
}
