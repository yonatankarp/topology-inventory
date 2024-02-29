plugins {
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.bom)
    alias(libs.plugins.kotlin.spring)
    `java-test-fixtures`
}

dependencies {
    api(project(":application"))

    implementation(libs.bundles.springboot.all)

    testImplementation(libs.bundles.test.all)
    testImplementation(testFixtures(project(":domain")))
}

tasks {
    jar {
        enabled = true
    }

    bootJar {
        enabled = false
    }
}
