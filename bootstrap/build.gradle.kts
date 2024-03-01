import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.bom)
    alias(libs.plugins.kotlin.spring)
}

group = "com.yonatankarp"
archivesName = "topology-inventory-springboot"
version = "1.0.0"

dependencies {
    api(project(":domain"))
    api(project(":application"))
    api(project(":adapters"))

    implementation(libs.bundles.kotlin.all)
    implementation(libs.bundles.springboot.all)
    runtimeOnly(libs.h2)
}

tasks {
    jar {
        enabled = false
    }
}
