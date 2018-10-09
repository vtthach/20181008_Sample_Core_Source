package com.sf0404.common.properties;


import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppPropertiesModule {

    private String sdCardPath;
    private String assetPath;

    public AppPropertiesModule(String sdCardPath, String assetPath) {
        this.sdCardPath = sdCardPath;
        this.assetPath = assetPath;
    }

    @Provides
    @Singleton
    @Named(ConstProperties.NAMED_PROPERTY_FILE_PATH)
    String providePropertiesFilePath() {
        return sdCardPath;
    }

    @Provides
    @Singleton
    @Named(ConstProperties.NAMED_PROPERTY_ASSET_PATH)
    String providePropertiesAssetPath() {
        return assetPath;
    }

    @Provides
    @Singleton
    @Named(ConstProperties.NAMED_PROPERTY)
    AppProperties provideAppProperties(Context context, @Named(ConstProperties.NAMED_PROPERTY_FILE_PATH) String sdCardFilePath,
                                       @Named(ConstProperties.NAMED_PROPERTY_ASSET_PATH) String assetPath) {
        return new AppPropertiesImpl(context, sdCardFilePath, assetPath);
    }

    @Provides
    @Singleton
    @Named(ConstProperties.NAMED_AUTOMATION_PROPERTY)
    AppProperties provideAutomationProperties(Context context,
                                              @Named(ConstProperties.NAMED_AUTOMATION_PROPERTY_FILE_PATH) String sdCardFilePath,
                                              @Named(ConstProperties.NAMED_AUTOMATION_PROPERTY_ASSET_PATH) String assetPath) {
        return new AppPropertiesImpl(context, sdCardFilePath, assetPath);
    }
}
