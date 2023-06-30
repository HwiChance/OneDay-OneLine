package com.hwichance.onedayoneline.start

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.hwichance.design.theme.OneDayOneLineTheme

@Composable
fun StartApp() {
    OneDayOneLineTheme {
        val navController = rememberNavController()
        StartNavHost(navController = navController)
    }
}
