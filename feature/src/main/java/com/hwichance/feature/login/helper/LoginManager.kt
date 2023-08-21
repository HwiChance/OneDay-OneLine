package com.hwichance.feature.login.helper

import com.hwichance.util.enums.LoginType

interface LoginManager {
    fun initialize()
    fun login(type: LoginType, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit)
    fun logout()
    fun unlink()
    fun isLoggedIn(): Boolean
}
