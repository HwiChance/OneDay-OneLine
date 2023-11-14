package com.hwichance.feature.login.helper

import com.hwichance.util.enums.LoginType

interface LoginManager {
    fun initialize()
    suspend fun login(type: LoginType, onSuccess: (String) -> Unit, onError: (Throwable) -> Unit)
    fun logout()
    fun unlink(type: LoginType)
    suspend fun isLoggedIn(type: LoginType): Boolean
}
