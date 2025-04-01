package com.appinfo

import com.facebook.react.bridge.*
import com.appinfo.AppInfoImpl
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.turbomodule.core.interfaces.TurboModule
import com.appinfo.NativeAppInfoSpec

// Helps identify the module
@ReactModule(name = AppInfoModule.NAME)
class AppInfoModule(reactContext: ReactApplicationContext) : NativeAppInfoSpec(reactContext), TurboModule {

    // Static constant to store the module name. This is what will be used to expose this module from NativeModules in react native
    companion object {
        const val NAME = "AppInfo"
    }

    // Override the getName method to return the name of the module we have set above.
    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    override fun getAppVersion(promise: Promise) {
        AppInfoImpl.getAppVersion(reactApplicationContext, promise, "no")
    }
}

