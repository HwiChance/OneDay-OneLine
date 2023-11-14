package com.hwichance.onedayoneline.di.auth

import com.hwichance.feature.login.helper.GoogleLoginHelper
import com.hwichance.feature.login.helper.LoginHelper
import com.hwichance.feature.login.helper.LoginManager
import com.hwichance.feature.login.helper.LoginManagerImpl
import com.hwichance.feature.login.helper.NaverLoginHelper
import com.hwichance.util.annotation.LoginHelperType
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class AuthHelperModule {
    @Binds
    @ActivityScoped
    @LoginHelperType("naver")
    abstract fun provideNaverLoginHelper(naverLoginHelper: NaverLoginHelper): LoginHelper

    @Binds
    @ActivityScoped
    @LoginHelperType("google")
    abstract fun provideGoogleLoginHelper(googleLoginHelper: GoogleLoginHelper): LoginHelper

    @Binds
    @ActivityScoped
    abstract fun provideLoginManager(loginManagerImpl: LoginManagerImpl): LoginManager
}
