package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.rica.agentverification.injection.AgentVerificationBuilderModule;
import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;
import com.innovation.rain.feature.rica.home.injection.RicaHomeBuilderModule;
import com.innovation.rain.feature.rica.home.injection.SampleBuilderModule;
import com.innovation.rain.feature.selectQuantity.injection.SelectQuantityBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class,
        RicaHomeBuilderModule.class,
        AgentVerificationBuilderModule.class,
        SelectQuantityBuilderModule.class,
        SampleBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
