package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.scaniddoc.scan.idbook.injection.ScanIdBookBuilderModule;
import com.innovation.rain.feature.collection.scaniddoc.scan.idcard.injection.ScanIdCardBuilderModule;
import com.innovation.rain.feature.collection.scaniddoc.scan.passport.injection.ScanPassportBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class,
        ScanIdBookBuilderModule.class,
        ScanIdCardBuilderModule.class,
        ScanPassportBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
