package com.hwichance.feature.login.helper

import com.hwichance.util.enums.LoginType
import javax.inject.Inject

class LoginManagerImpl @Inject constructor(
    private val googleLoginHelper: LoginHelper,
) : LoginManager {
    override fun initialize() {
        // TODO("Not yet implemented")
    }

    override fun login(
        type: LoginType,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        // TODO("Not yet implemented")
    }

    override fun logout() {
        // TODO("Not yet implemented")
    }

    override fun unlink() {
        // TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        return googleLoginHelper.isLoggedIn()
    }
}
