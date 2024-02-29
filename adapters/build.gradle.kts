plugins {
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.bom)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    api(project(":application"))

    implementation(libs.bundles.springboot.all)

    testImplementation(libs.bundles.test.all)
}

tasks {
    jar {
        enabled = false
    }

    bootJar {
        enabled = false
    }
}
