//
//  FLRNImageFromRNAdapter.m
//  Fidel
//
//  Created by Corneliu on 24/03/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "FLRNImageFromRNAdapter.h"


@implementation FLRNImageFromRNAdapter

-(UIImage *)imageFromRawData:(id)rawImageData {
    NSObject *bannerImageOption = rawImageData;
    UIImage *bannerImage = [[UIImage alloc] init];//[RCTConvert UIImage:bannerImageOption];
    return bannerImage;
}

@end
