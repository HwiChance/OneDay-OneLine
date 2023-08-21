package com.hwichance.feature.login.helper

import java.lang.Exception

interface LoginHelper {
    fun initialize()
    fun login(onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit)
    fun logout()
    fun unlink()
    fun isLoggedIn(): Boolean
}
