package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.injection.ScanIdBookBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.injection.ScanIdCardBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.injection.ScanPassportBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;
import com.innovation.rain.feature.rica.home.injection.RicaHomeBuilderModule;
import com.innovation.rain.feature.rica.home.injection.SampleBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.home.injection.ScanIdDocBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class,
        RicaHomeBuilderModule.class,
        SampleBuilderModule.class,
        ScanIdBookBuilderModule.class,
        ScanIdCardBuilderModule.class,
        ScanPassportBuilderModule.class,
        ScanIdDocBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
