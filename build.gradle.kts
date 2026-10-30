import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.spotless) apply true
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.ksp) apply false
}

subprojects {
    // Common Android Application Configuration
    pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension>("android") {
            compileSdk = 37
            defaultConfig {
                minSdk = 24
                targetSdk = 37
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
        }
    }

    // Common Android Library Configuration
    pluginManager.withPlugin("com.android.library") {
        extensions.configure<LibraryExtension>("android") {
            compileSdk = 37
            defaultConfig {
                minSdk = 24
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            buildFeatures {
                compose = true
            }
        }
    }

    // Spotless Configuration
    pluginManager.withPlugin("com.diffplug.spotless") {
        this@subprojects.configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt")
                targetExclude("**/build/**/*.kt")
                ktlint("1.2.1").editorConfigOverride(
                    mapOf(
                        "ktlint_standard_function-naming" to "disabled",
                        "ktlint_standard_no-wildcard-imports" to "enabled",
                        "ij_kotlin_use_import_with_star" to "false",
                        "ij_kotlin_use_import_with_star_for_members" to "false",
                    )
                )
            }
            kotlinGradle {
                target("*.gradle.kts")
                ktlint("1.2.1")
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
        }
    }
}
