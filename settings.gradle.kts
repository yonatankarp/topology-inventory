plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "topology-inventory"
include(
    "domain",
    "application",
    "adapters",
    "bootstrap",
)
