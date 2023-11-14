package com.hwichance.feature.login.helper

import android.content.Context
import com.hwichance.feature.R
import com.hwichance.util.exception.EmailNotFoundException
import com.hwichance.util.exception.LoginApiException
import com.hwichance.util.exception.LoginCancelException
import com.hwichance.util.exception.LoginNetworkException
import com.hwichance.util.exception.LoginUnknownException
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthErrorCode
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.NidOAuthLoginState
import com.navercorp.nid.oauth.NidOAuthPreferencesManager
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class NaverLoginHelper @Inject constructor(
    @ActivityContext private val activity: Context,
) : LoginHelper {
    override fun initialize() {
        if (!NaverIdLoginSDK.isInitialized()) {
            initializeSdk(activity)
        }
    }

    override suspend fun login(onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
        try {
            initialize()
            logout()
        } catch (t: Throwable) {
            onError(t)
        }

        NidOAuthPreferencesManager.initState = null

        NaverIdLoginSDK.authenticate(
            context = activity,
            callback = object : OAuthLoginCallback {
                override fun onError(errorCode: Int, message: String) {
                    NaverIdLoginSDK.oauthLoginCallback = null
                    onError(getErrorType())
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    NaverIdLoginSDK.oauthLoginCallback = null
                    onError(getErrorType())
                }

                override fun onSuccess() {
                    NaverIdLoginSDK.oauthLoginCallback = null
                    if (NidOAuthPreferencesManager.state != NidOAuthPreferencesManager.initState) {
                        logout()
                        onError(LoginUnknownException)
                    } else {
                        getUserEmail(onSuccess = onSuccess, onError = onError)
                    }
                }
            },
        )
    }

    override fun logout() {
        NaverIdLoginSDK.logout()
    }

    override fun unlink() {
        NidOAuthLogin().callDeleteTokenApi(object : OAuthLoginCallback {
            override fun onSuccess() {
                // 서버에서 토큰 삭제에 성공한 상태입니다.
            }

            override fun onFailure(httpStatus: Int, message: String) {
                // 서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                // 클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
            }

            override fun onError(errorCode: Int, message: String) {
                // 서버에서 토큰 삭제에 실패했어도 클라이언트에 있는 토큰은 삭제되어 로그아웃된 상태입니다.
                // 클라이언트에 토큰 정보가 없기 때문에 추가로 처리할 수 있는 작업은 없습니다.
                onFailure(errorCode, message)
            }
        })
    }

    override suspend fun isLoggedIn(): Boolean {
        initialize()

        return when (NaverIdLoginSDK.getState()) {
            NidOAuthLoginState.OK -> true
            NidOAuthLoginState.NEED_LOGIN, NidOAuthLoginState.NEED_INIT -> false
            NidOAuthLoginState.NEED_REFRESH_TOKEN -> {
                logout()
                false
            }
        }
    }

    private fun getUserEmail(onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
        NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
            override fun onError(errorCode: Int, message: String) {
                onError(getErrorType())
            }

            override fun onFailure(httpStatus: Int, message: String) {
                onError(getErrorType())
            }

            override fun onSuccess(result: NidProfileResponse) {
                val email = result.profile?.email ?: return onError(EmailNotFoundException)
                onSuccess(email)
            }
        })
    }

    private fun getErrorType(): Throwable {
        return when (NaverIdLoginSDK.getLastErrorCode()) {
            NidOAuthErrorCode.CLIENT_USER_CANCEL -> LoginCancelException
            NidOAuthErrorCode.CLIENT_ERROR_CONNECTION_ERROR -> LoginNetworkException
            else -> LoginApiException
        }
    }

    companion object {
        fun initializeSdk(context: Context) {
            NaverIdLoginSDK.showDevelopersLog(true)
            try {
                val clientId = context.getString(R.string.naver_client_id)
                val clientSecret = context.getString(R.string.naver_client_secret)
                val clientName = context.getString(R.string.naver_client_name)
                NaverIdLoginSDK.initialize(
                    context = context,
                    clientId = clientId,
                    clientSecret = clientSecret,
                    clientName = clientName,
                )
            } catch (e: Exception) {
                throw LoginApiException
            }
        }
    }
}
