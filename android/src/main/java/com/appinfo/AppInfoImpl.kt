package com.appinfo

import android.app.Activity
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.core.content.pm.PackageInfoCompat
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.Arguments

class AppInfoImpl {
    companion object {
        @JvmStatic
        fun getAppVersion(reactContext: ReactApplicationContext, promise: Promise, isold: String) {
            try {
                val manager = reactContext.packageManager
                val info = manager.getPackageInfo(reactContext.packageName, 0)

                val versionName = info.versionName
                val versionCode = PackageInfoCompat.getLongVersionCode(info)

                // Create a WritableMap to send an object
                val versionInfo: WritableMap = Arguments.createMap()
                versionInfo.putString("versionName", versionName)
                versionInfo.putDouble("versionCode", versionCode.toDouble()) // JavaScript uses double for numbers
                versionInfo.putString("isOld", isold)
                promise.resolve(versionInfo) // Return the object
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                promise.reject("ERROR", "Failed to get app version", e)
            }
        }
    }
}
