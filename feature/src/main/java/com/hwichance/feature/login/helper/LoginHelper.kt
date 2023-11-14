package com.hwichance.feature.login.helper

interface LoginHelper {
    fun initialize()
    suspend fun login(onSuccess: (String) -> Unit, onError: (Throwable) -> Unit)
    fun logout()
    fun unlink()
    suspend fun isLoggedIn(): Boolean
}
