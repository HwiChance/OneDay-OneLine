@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.diffplug.spotless)
}

allprojects {
    apply(plugin = rootProject.libs.plugins.diffplug.spotless.get().pluginId)
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint(libs.versions.ktlint.get()).userData(
                mapOf(
                    "android" to "true",
                    "ktlint_code_style" to "android",
                    "ij_kotlin_allow_trailing_comma" to "true",
                ),
            )
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint(libs.versions.ktlint.get()).userData(
                mapOf(
                    "android" to "true",
                    "ktlint_code_style" to "android",
                    "ij_kotlin_allow_trailing_comma" to "true",
                ),
            )
        }
    }
}
