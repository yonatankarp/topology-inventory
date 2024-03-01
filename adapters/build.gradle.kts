plugins {
    alias(libs.plugins.kotlin.spring)
    `java-test-fixtures`
}

dependencies {
    api(project(":application"))

    implementation(libs.jakarta.persistence.api)

    testImplementation(libs.bundles.test.all)
    testImplementation(testFixtures(project(":domain")))
}
