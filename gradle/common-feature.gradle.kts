import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


// Shared configuration for every feature library module.
// Single source of truth for versions: gradle/libs.versions.toml

// Kotlin serialization: required for @Serializable navigation routes (Section 3 of AGENT_INSTRUCTIONS.md).
pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun lib(alias: String) = catalog.findLibrary(alias).get()

project.dependencies.add("implementation", lib("androidx-core-ktx"))
project.dependencies.add("implementation", lib("androidx-lifecycle-runtime-ktx"))
project.dependencies.add("implementation", lib("androidx-activity-compose"))
project.dependencies.add("implementation", lib("jspecify"))

project.dependencies.add("implementation", project.dependencies.platform(lib("androidx-compose-bom")))
project.dependencies.add("implementation", lib("androidx-compose-ui"))
project.dependencies.add("implementation", lib("androidx-compose-ui-graphics"))
project.dependencies.add("implementation", lib("androidx-compose-ui-tooling-preview"))
project.dependencies.add("implementation", lib("androidx-compose-material3"))
project.dependencies.add("implementation", lib("androidx-compose-material-icons-core"))
project.dependencies.add("implementation", lib("androidx-compose-material-icons-extended"))
project.dependencies.add("debugImplementation", lib("androidx-compose-ui-tooling"))

project.dependencies.add("implementation", lib("koin-android"))
project.dependencies.add("implementation", lib("koin-androidx-compose"))
project.dependencies.add("implementation", lib("androidx-navigation-compose"))
project.dependencies.add("implementation", lib("kotlinx-serialization-json"))

project.dependencies.add("testImplementation", lib("junit"))
project.dependencies.add("testImplementation", lib("mockk"))
project.dependencies.add("testImplementation", lib("kotlinx-coroutines-test"))
project.dependencies.add("testImplementation", lib("turbine"))
project.dependencies.add("androidTestImplementation", lib("androidx-junit"))
project.dependencies.add("androidTestImplementation", lib("androidx-compose-ui-test-junit4"))
