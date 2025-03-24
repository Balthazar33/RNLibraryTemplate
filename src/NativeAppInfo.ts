import type {TurboModule} from 'react-native';
import {TurboModuleRegistry, NativeModules} from 'react-native';
const {AppInfo: AppInfoLegacy} = NativeModules;
export interface Spec extends TurboModule {
  getAppVersion(): Promise<{versionName: string; versionCode: number}>;
}

const isNewArchEnabled = global.__turboModuleProxy != null;
const AppInfo = isNewArchEnabled ? TurboModuleRegistry.getEnforcing<Spec>('AppInfo') : AppInfoLegacy;

export default AppInfo;
