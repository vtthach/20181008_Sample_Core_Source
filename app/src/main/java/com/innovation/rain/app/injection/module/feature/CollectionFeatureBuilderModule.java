package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;
import com.innovation.rain.feature.rica.address.injection.ManualAddressBuilderModule;
import com.innovation.rain.feature.rica.agentverification.injection.AgentVerificationBuilderModule;
import com.innovation.rain.feature.rica.home.injection.RicaHomeBuilderModule;
import com.innovation.rain.feature.selectquantity.injection.SelectQuantityBuilderModule;
import com.innovation.rain.feature.rica.poa.injection.ProofOfAddressBuilderModule;
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
        SelectQuantityBuilderModule.class,
        ManualAddressBuilderModule.class,
        ProofOfAddressBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
