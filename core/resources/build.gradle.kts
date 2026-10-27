plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

apply(from = "$rootDir/gradle/common-feature.gradle.kts")

android {
    namespace = "com.example.codechallenge.core.resources"
}
