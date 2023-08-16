package com.hwichance.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hwichance.design.component.LoginButton
import com.hwichance.design.component.LogoImage
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.design.theme.bmEulJiro
import com.hwichance.feature.R
import com.hwichance.util.enums.LoginType

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = OneDayOneLineTheme.typography.bigTitle1.copy(fontFamily = bmEulJiro),
                )
                LoginButtonContent()
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        LogoImage(modifier = Modifier.height(50.dp))
        Spacer(modifier = Modifier.size(25.dp))
    }
}

@Composable
fun LoginButtonContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginButton(
            type = LoginType.KAKAO,
            onClick = {},
        )
        LoginButton(
            type = LoginType.NAVER,
            onClick = {},
        )
        LoginButton(
            type = LoginType.GOOGLE,
            onClick = {},
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun LoginScreenPreview() {
    OneDayOneLineTheme {
        LoginScreen()
    }
}
