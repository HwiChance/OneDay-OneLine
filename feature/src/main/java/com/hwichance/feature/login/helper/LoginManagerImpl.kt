package com.hwichance.feature.login.helper

import com.hwichance.util.annotation.LoginHelperType
import com.hwichance.util.enums.LoginType
import javax.inject.Inject

class LoginManagerImpl @Inject constructor(
    @LoginHelperType("naver") private val naverLoginHelper: LoginHelper,
    @LoginHelperType("google") private val googleLoginHelper: LoginHelper,
) : LoginManager {
    override fun initialize() {
        LoginType.values()
            .forEach {
                mapToLoginHelper(it).initialize()
            }
    }

    override suspend fun login(
        type: LoginType,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        mapToLoginHelper(type).login(onSuccess, onError)
    }

    override fun logout() {
        LoginType.values()
            .map {
                mapToLoginHelper(it)
            }
            .forEach {
                it.logout()
            }
    }

    override fun unlink(type: LoginType) {
        mapToLoginHelper(type).unlink()
    }

    override suspend fun isLoggedIn(type: LoginType): Boolean {
        return runCatching {
            mapToLoginHelper(type).isLoggedIn()
        }.getOrDefault(false)
    }

    private fun mapToLoginHelper(type: LoginType): LoginHelper {
        return when (type) {
            LoginType.NAVER -> naverLoginHelper
            LoginType.GOOGLE -> googleLoginHelper
            LoginType.KAKAO -> googleLoginHelper // TODO: change to kakao login helper
        }
    }
}
