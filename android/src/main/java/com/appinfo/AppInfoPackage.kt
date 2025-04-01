package com.appinfo

import com.facebook.react.BaseReactPackage
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.appinfo.BuildConfig

class AppInfoPackage : BaseReactPackage(), ReactPackage {
    private val modules = listOf(
      AppInfoModule::class.java
    )
    
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        return if (name == AppInfoModule.NAME) {
            AppInfoModule(reactContext)
        } else {
            null
        }
    }

    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(AppInfoModule(reactContext)) // Required for old architecture
    }

    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        return ReactModuleInfoProvider {
            val moduleInfos = mutableMapOf<String, ReactModuleInfo>()
            modules.forEach { moduleClass ->
                val moduleName = moduleClass.simpleName
                moduleInfos[moduleName] = ReactModuleInfo(
                moduleName,
                moduleName,
                false,  // canOverrideExistingModule
                false,  // needsEagerInit
                false,  // isCxxModule
                true // isTurboModule
                )
            }
            moduleInfos
        }
    }

    companion object {
        fun create(): ReactPackage {
          val isNewArchEnabled = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED 
            return if (isNewArchEnabled) {
                TurboModuleAppInfoPackage() // Use TurboModules in the new architecture
            } else {
                AppInfoPackage() // Legacy architecture
            }
        }
    }
}
