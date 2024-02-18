import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless)
}

repositories {
    mavenCentral()
}

subprojects {

    apply {
        plugin("kotlin")
        plugin("com.diffplug.spotless")
    }

    kotlin {
        jvmToolchain(21)
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configure<SpotlessExtension> {
        kotlin {
            // see https://github.com/shyiko/ktlint#standard-rules
            ktlint()
        }
        kotlinGradle {
            trimTrailingWhitespace()
            ktlint()
        }
    }
}
