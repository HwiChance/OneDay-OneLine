package com.hwichance.onedayoneline.start

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hwichance.feature.splash.SplashScreen

@Composable
fun StartNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination.route,
    ) {
        composable(route = SplashScreenDestination.route) {
            SplashScreen()
        }
    }
}
