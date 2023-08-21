package com.hwichance.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hwichance.design.component.LogoImage
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.feature.common.ScreenDestination.LoginScreenDestination
import com.hwichance.feature.common.mvvm.event.LifecycleEvent
import com.hwichance.feature.common.mvvm.event.NavigationEvent
import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.feature.splash.viewmodel.SplashViewModel
import com.hwichance.util.lifecycle.LifecycleEffect
import com.hwichance.util.navigateWithPop
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavHostController,
    navigateToHome: () -> Unit,
) {
    ScreenNavigator(
        navigationEventFlow = viewModel.navigationEventFlow,
        navController = navController,
        navigateToHome = navigateToHome,
    )

    LifecycleEffect { event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.sendUiEvent(LifecycleEvent.OnResume)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LogoImage(modifier = Modifier.width(250.dp))
    }
}

@Composable
private fun ScreenNavigator(
    navigationEventFlow: SharedFlow<UiEvent>,
    navController: NavHostController,
    navigateToHome: () -> Unit,
) {
    LaunchedEffect(Unit) {
        navigationEventFlow
            .filterIsInstance<NavigationEvent>()
            .onEach {
                when (it) {
                    NavigationEvent.NavigateToHome -> navigateToHome.invoke()
                    else -> navController.navigateWithPop(LoginScreenDestination.route)
                }
            }
            .launchIn(this)
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun SplashScreenPreview() {
    OneDayOneLineTheme {
        SplashScreen(navController = rememberNavController(), navigateToHome = {})
    }
}
