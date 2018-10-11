package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.collection.signin.injection.ClientSignInBuilderModule;

import dagger.Module;

@Module(includes = {
        ClientSignInBuilderModule.class,
})
public class CollectionFeatureBuilderModule {
    // Use later
}
