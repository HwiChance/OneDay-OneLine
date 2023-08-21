package com.hwichance.feature.login.helper

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleLoginHelper @Inject constructor(
    @ApplicationContext private val context: Context,
) : LoginHelper {

    override fun initialize() {}

    override fun login(onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        // TODO("Not yet implemented")
    }

    override fun logout() {
        // TODO("Not yet implemented")
    }

    override fun unlink() {
        // TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(context) != null
    }
}
