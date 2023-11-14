package com.hwichance.feature.login.helper

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.hwichance.util.exception.EmailNotFoundException
import com.hwichance.util.exception.LoginApiException
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class GoogleLoginHelper @Inject constructor(
    @ActivityContext private val context: Context,
) : LoginHelper {
    private val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private lateinit var launcher: ActivityResultLauncher<Intent>

    private var successCallback: ((String) -> Unit)? = null
    private var errorCallback: ((Throwable) -> Unit)? = null

    override fun initialize() {
        val activity = context as? ComponentActivity ?: return
        launcher =
            activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                try {
                    val data = result.data ?: throw LoginApiException
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account = task.getResult(ApiException::class.java)
                    val email = account.email ?: throw EmailNotFoundException
                    handleLoginSuccess(email)
                } catch (t: Throwable) {
                    handleLoginError(
                        throwable = when (t) {
                            is ApiException -> LoginApiException
                            else -> t
                        },
                    )
                }
            }
    }

    override suspend fun login(onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
        successCallback = onSuccess
        errorCallback = onError
        try {
            val intent = GoogleSignIn.getClient(context, options).signInIntent
            launcher.launch(intent)
        } catch (t: Throwable) {
            onError(t)
        }
    }

    private fun handleLoginSuccess(email: String) {
        successCallback?.invoke(email)
    }

    private fun handleLoginError(throwable: Throwable) {
        errorCallback?.invoke(throwable)
    }

    override fun logout() {
        GoogleSignIn.getClient(context, options)
            .signOut()
    }

    override fun unlink() {
        GoogleSignIn.getClient(context, options)
            .revokeAccess()
    }

    override suspend fun isLoggedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(context) != null
    }
}
