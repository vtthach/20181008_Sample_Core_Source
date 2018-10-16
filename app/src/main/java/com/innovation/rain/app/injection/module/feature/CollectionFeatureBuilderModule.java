package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.orders.injection.OrderListBuilderModule;
import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;
import com.innovation.rain.feature.rica.dashboard.injection.DashboardBuilderModule;
import com.innovation.rain.feature.rica.dashboard.injection.SampleBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
        OrderListBuilderModule.class,
        DashboardBuilderModule.class,
        SampleBuilderModule.class
})
public class CollectionFeatureBuilderModule {
    // Use later
}
