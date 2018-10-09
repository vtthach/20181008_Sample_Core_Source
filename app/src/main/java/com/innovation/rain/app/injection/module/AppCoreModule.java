package com.innovation.rain.app.injection.module;


import com.amb.retrofitwrapper.RetrofitConstants;
import com.amb.retrofitwrapper.module.OkHttpModule;
import com.amb.retrofitwrapper.module.RetrofitModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.innovation.rain.BuildConfig;
import com.innovation.rain.app.prefs.AppPreferenceImpl;
import com.innovation.rain.app.properties.BuildInProperties;
import com.sf0404.common.prefs.AppPreferences;
import com.sf0404.common.properties.AppProperties;
import com.sf0404.common.properties.AppPropertiesModule;
import com.sf0404.common.properties.ConstProperties;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {
        AndroidSupportInjectionModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        HelperModule.class,
        AppPropertiesModule.class
})
public class AppCoreModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    @Singleton
    BuildInProperties provideBuildInProperties(@Named(ConstProperties.NAMED_PROPERTY) AppProperties appProperties) {
        return new BuildInProperties(appProperties);
    }

    @Provides
    @Singleton
    AppPreferences provideAppPreference(AppPreferenceImpl appPreferences) {
        return appPreferences;
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.ENABLE_LOG)
    boolean provideEnableLog() {
        return BuildConfig.DEBUG;
    }
}

