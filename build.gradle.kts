import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless) apply true
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    // Spotless Consifiguration
    pluginManager.withPlugin("com.diffplug.spotless") {
        this@subprojects.configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/**/*.kt")
                ktlint("1.2.1").editorConfigOverride(
                    mapOf(
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
                    )
                )
            }
            kotlinGradle {
                target("*.gradle.kts")
                ktlint()
            }
        }
    }

    // Detekt Configuration
    pluginManager.withPlugin("io.gitlab.arturbosch.detekt") {
        this@subprojects.configure<DetektExtension> {
            buildUponDefaultConfig = true
            allRules = false
            config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        }
        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            jvmTarget = "17"
            doFirst {
                System.setProperty("java.version", "21.0.0")
            }
        }
    }
}