dependencies {
    api(project(":domain"))

    testImplementation(libs.bundles.test.all)
    testImplementation(libs.bundles.cucumber.all)
}
