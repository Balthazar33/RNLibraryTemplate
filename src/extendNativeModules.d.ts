import 'react-native';

export interface AppInfoModuleInterface {
  getAppVersion(): Promise<{versionName: string; versionCode: number}>;
}

declare module 'react-native' {
  interface NativeModulesStatic {
    AppInfo: AppInfoModuleInterface;
  }
}
