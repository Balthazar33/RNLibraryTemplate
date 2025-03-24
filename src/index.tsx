import AppInfo from './NativeAppInfo';

// export function getAppVersion(): Promise<{versionName: string; versionCode: number}> {
//   return AppInfo.getAppVersion();
// }

export default {
  getAppVersion: AppInfo.getAppVersion,
}
