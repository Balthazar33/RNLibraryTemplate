package com.appinfo

import android.app.Activity
import com.facebook.react.bridge.Promise
import com.appinfo.AppInfoImpl
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.turbomodule.core.interfaces.TurboModule

// Helps identify the module
@ReactModule(name = AppInfoModule.NAME)
class AppInfoModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext), TurboModule {

    // Static constant to store the module name. This is what will be used to expose this module from NativeModules in react native
    companion object {
        const val NAME = "AppInfo"
    }

    // Override the getName method to return the name of the module we have set above.
    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    fun getAppVersion(promise: Promise) {
        AppInfoImpl.getAppVersion(reactApplicationContext, promise, "no")
    }
}

