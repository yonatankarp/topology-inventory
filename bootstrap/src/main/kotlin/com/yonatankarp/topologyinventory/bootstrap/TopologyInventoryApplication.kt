package com.yonatankarp.topologyinventory.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TopologyInventoryApplication

fun main(args: Array<String>) {
    runApplication<TopologyInventoryApplication>(*args)
}
