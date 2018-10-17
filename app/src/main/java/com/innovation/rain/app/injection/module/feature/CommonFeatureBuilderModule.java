package com.innovation.rain.app.injection.module.feature;

import com.innovation.rain.feature.agentlogin.injection.AgentLoginBuilderModule;
import com.innovation.rain.feature.welcomemenu.injection.WelcomeMenuBuilderModule;

import dagger.Module;

@Module(includes = {
        AgentLoginBuilderModule.class,
        WelcomeMenuBuilderModule.class
})
public class CommonFeatureBuilderModule {
    // Use later -> App scope
}
