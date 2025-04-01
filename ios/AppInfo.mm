#import "AppInfo.h"
#import <React/RCTLog.h>
#ifdef RCT_NEW_ARCH_ENABLED
#import "<GeneratedSpec>.h"
#endif

@implementation AppInfo

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getAppVersion:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  @try {
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *versionName = infoDictionary[@"CFBundleShortVersionString"];
    NSString *versionCode = infoDictionary[@"CFBundleVersion"];

    if (versionName && versionCode) {
      NSDictionary *versionInfo = @{
        @"versionName": versionName,
        @"versionCode": versionCode
      };
      resolve(versionInfo);
    } else {
      NSError *error = [NSError errorWithDomain:@"AppInfo"
                                           code:1001
                                       userInfo:@{NSLocalizedDescriptionKey:@"Version information not found"}];
      reject(@"no_version_info", @"Version information not found", error);
    }
  }
  @catch (NSException *exception) {
    NSError *error = [NSError errorWithDomain:@"AppInfo"
                                         code:1002
                                     userInfo:@{NSLocalizedDescriptionKey:exception.reason}];
    reject(@"exception", exception.reason, error);
  }
}

#ifdef RCT_NEW_ARCH_ENABLED
 - (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
 {
    return std::make_shared<facebook::react::<NativeAppInfoSpecJSI>>(params);
 }
#endif

@end
