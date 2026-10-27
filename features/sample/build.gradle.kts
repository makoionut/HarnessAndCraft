plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
}

apply(from = "$rootDir/gradle/common-feature.gradle.kts")

android {
    namespace = "com.example.codechallenge.features.sample"
}

dependencies {
    implementation(project(":core:resources"))
}
