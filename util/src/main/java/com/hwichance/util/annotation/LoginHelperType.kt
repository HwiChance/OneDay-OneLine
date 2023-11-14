package com.hwichance.util.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginHelperType(val value: String = "")
