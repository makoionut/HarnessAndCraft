plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.harnessandroid.core.common"
}

dependencies {
    implementation(project(":core:resources"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.common)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
}
