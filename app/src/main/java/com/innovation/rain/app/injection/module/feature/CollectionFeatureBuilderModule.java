package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
