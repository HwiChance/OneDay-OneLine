package com.hwichance.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hwichance.design.component.LogoImage
import com.hwichance.design.theme.OneDayOneLineTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LogoImage(modifier = Modifier.width(250.dp))
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun SplashScreenPreview() {
    OneDayOneLineTheme {
        SplashScreen()
    }
}
