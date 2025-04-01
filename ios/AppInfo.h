
#import <React/RCTBridgeModule.h>
#ifdef RCT_NEW_ARCH_ENABLED
#import <NativeAppInfoSpec/NativeAppInfoSpec.h>
#endif

@interface AppInfo : NSObject <RCTBridgeModule>
@end

#ifdef RCT_NEW_ARCH_ENABLED
@interface AppInfo () <NativeAppInfoSpec>

@end
#endif