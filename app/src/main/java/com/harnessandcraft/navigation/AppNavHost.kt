package com.harnessandcraft.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.harnessandcraft.core.resources.R
import com.harnessandcraft.features.sample.Greeting

/**
 * Central Navigation Host owned by the `:app` module.
 * Orchestrates navigation across feature modules using contract-based lambdas and NavController.
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Sample.route,
        modifier = modifier,
    ) {
        composable(Screen.Sample.route) {
            Greeting(
                name = stringResource(R.string.greeting_android),
            )
        }
    }
}
