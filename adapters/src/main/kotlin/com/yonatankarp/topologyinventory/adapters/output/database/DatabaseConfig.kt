package com.yonatankarp.topologyinventory.adapters.output.database

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {
    @Bean
    fun entityManagerH2(): EntityManager =
        Persistence
            .createEntityManagerFactory(DATABASE_NAME)
            .createEntityManager()

    companion object {
        private const val DATABASE_NAME = "inventory"
    }
}
