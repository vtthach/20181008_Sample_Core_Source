package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;
import com.innovation.rain.feature.rica.poa.address.injection.ManualAddressBuilderModule;
import com.innovation.rain.feature.rica.agentverification.injection.AgentVerificationBuilderModule;
import com.innovation.rain.feature.rica.home.injection.RicaHomeBuilderModule;
import com.innovation.rain.feature.order.create.injection.CreateOrderBuilderModule;
import com.innovation.rain.feature.rica.poa.home.injection.ProofOfAddressBuilderModule;
import com.innovation.rain.feature.rica.poa.scan.injection.ScanPOABuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.home.injection.RicaHomeScanIdDocBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.injection.ScanIdBookBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.injection.ScanIdCardBuilderModule;
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.injection.ScanPassportBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class,
        RicaHomeBuilderModule.class,
        ScanIdBookBuilderModule.class,
        ScanIdCardBuilderModule.class,
        ScanPassportBuilderModule.class,
        RicaHomeScanIdDocBuilderModule.class,
        AgentVerificationBuilderModule.class,
        CreateOrderBuilderModule.class,
        ManualAddressBuilderModule.class,
        ScanPOABuilderModule.class,
        ProofOfAddressBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
