package com.innovation.rain.app.injection.module;

import com.innovation.rain.feature.agentlogin.injection.AgentLoginBuilderModule;

import dagger.Module;

@Module(includes = {
        AgentLoginBuilderModule.class,
})
public class FeatureBuilderModule {

}
