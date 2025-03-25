// #import "AppInfo.h"

// @implementation AppInfo
// RCT_EXPORT_MODULE()

// - (NSNumber *)multiply:(double)a b:(double)b {
//     NSNumber *result = @(a * b);

//     return result;
// }

// - (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
//     (const facebook::react::ObjCTurboModule::InitParams &)params
// {
//     return std::make_shared<facebook::react::NativeAppInfoSpecJSI>(params);
// }

// @end
// TODO:

#import "AppInfo.h"
#import <React/RCTLog.h>

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

@end
