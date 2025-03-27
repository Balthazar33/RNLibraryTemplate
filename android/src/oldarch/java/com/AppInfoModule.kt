package com.appinfo

import com.appinfo.AppInfoImpl
import com.facebook.react.bridge.*
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = AppInfoModule.NAME)
class AppInfoModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    
    companion object {
        const val NAME = "AppInfo"
    }

    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    fun getAppVersion(promise: Promise) {
        AppInfoImpl.getAppVersion(reactApplicationContext, promise, "yes")
    }
}
