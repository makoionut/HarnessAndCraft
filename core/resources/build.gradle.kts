plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.harnessandcraft.core.resources"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.common)
    implementation(libs.bundles.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
}
