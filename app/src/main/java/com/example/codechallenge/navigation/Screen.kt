package com.example.codechallenge.navigation

/**
 * Defines destination screens and routes for the application navigation graph.
 * Future feature routes should be added here or in `:core:common` if shared.
 */
sealed interface Screen {
    val route: String

    data object Sample : Screen {
        override val route = "sampleScreen"
    }
}
