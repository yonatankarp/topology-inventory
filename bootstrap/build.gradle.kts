import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.bom)
    alias(libs.plugins.kotlin.spring)
}

group = "com.yonatankarp"
archivesName = "topology-inventory"
version = "1.0.0"

dependencies {
    implementation(libs.springboot.web.starter)
}

tasks {
    jar {
        enabled = false
    }
}
