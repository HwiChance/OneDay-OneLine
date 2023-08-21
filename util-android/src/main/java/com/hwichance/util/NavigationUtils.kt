package com.hwichance.util

import androidx.navigation.NavHostController

fun NavHostController.navigateWithPop(route: String) {
    popBackStack()
    navigate(route = route)
}
