package com.hwichance.feature.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.hwichance.design.component.LoginButton
import com.hwichance.design.component.LogoImage
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.design.theme.bmEulJiro
import com.hwichance.feature.R
import com.hwichance.feature.common.mvvm.event.NavigationEvent
import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.feature.login.viewmodel.LoginUiEvent.LoginResultEvent.OnLoginFailure
import com.hwichance.feature.login.viewmodel.LoginUiEvent.LoginResultEvent.OnLoginSuccess
import com.hwichance.feature.login.viewmodel.LoginViewModel
import com.hwichance.util.enums.EmailNotFoundException
import com.hwichance.util.enums.LoginApiException
import com.hwichance.util.enums.LoginCancelException
import com.hwichance.util.enums.LoginType
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    ScreenNavigator(
        navigationEventFlow = viewModel.navigationEventFlow,
        navigateToHome = navigateToHome,
    )

    ErrorHandler(errorEventFlow = viewModel.errorEventFlow)

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
                LoginButtonContent(viewModel::sendUiEvent)
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        LogoImage(modifier = Modifier.height(50.dp))
        Spacer(modifier = Modifier.size(25.dp))
    }
}

@Composable
private fun ScreenNavigator(navigationEventFlow: SharedFlow<UiEvent>, navigateToHome: () -> Unit) {
    LaunchedEffect(Unit) {
        navigationEventFlow
            .filterIsInstance<NavigationEvent>()
            .onEach {
                navigateToHome.invoke()
            }
            .launchIn(this)
    }
}

@Composable
private fun ErrorHandler(errorEventFlow: SharedFlow<Int>) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        errorEventFlow
            .filterIsInstance<Int>()
            .onEach {
                Toast.makeText(context, context.getString(it), Toast.LENGTH_SHORT).show()
            }
            .launchIn(this)
    }
}

@Composable
private fun LoginButtonContent(
    sendUiEvent: (UiEvent) -> Unit,
) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let {
                try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(it)
                    val account = task.getResult(ApiException::class.java)
                    val email = account.email ?: throw EmailNotFoundException
                    sendUiEvent(OnLoginSuccess(email))
                } catch (e: Exception) {
                    val exception = when (e) {
                        is ApiException -> LoginApiException
                        else -> e
                    }
                    sendUiEvent(OnLoginFailure(exception))
                }
            } ?: sendUiEvent(OnLoginFailure(LoginCancelException))
        }
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
            onClick = {
                val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
                val intent = GoogleSignIn.getClient(context, options).signInIntent
                launcher.launch(intent)
            },
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun LoginScreenPreview() {
    OneDayOneLineTheme {
        LoginScreen(navigateToHome = {})
    }
}
