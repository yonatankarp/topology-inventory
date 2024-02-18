package com.yonatankarp.topologyinventory.domain.service

import com.yonatankarp.topologyinventory.domain.entity.Switch
import com.yonatankarp.topologyinventory.domain.valueobject.Id

object SwitchService {
    fun filterAndRetrieveSwitch(
        switches: List<Switch>,
        predicate: (Switch) -> Boolean,
    ): List<Switch> = switches.filter(predicate).toList()

    fun findById(
        switches: Map<Id, Switch>,
        id: Id,
    ): Switch? = switches[id]
}
