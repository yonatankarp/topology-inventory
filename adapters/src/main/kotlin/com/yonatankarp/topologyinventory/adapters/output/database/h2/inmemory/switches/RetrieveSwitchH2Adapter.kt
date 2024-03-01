package com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.switches

import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.data.SwitchData
import com.yonatankarp.topologyinventory.adapters.output.database.h2.inmemory.mappers.SwitchMapper.toDomain
import com.yonatankarp.topologyinventory.application.ports.output.switchs.RetrieveSwitchPort
import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id
import jakarta.persistence.EntityManager

class RetrieveSwitchH2Adapter(
    private val entityManager: EntityManager,
) : RetrieveSwitchPort {
    override fun invoke(id: Id): Switch = entityManager.getReference(SwitchData::class.java, id.value).toDomain()
}
