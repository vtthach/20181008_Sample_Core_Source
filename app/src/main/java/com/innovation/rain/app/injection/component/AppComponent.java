package com.innovation.rain.app.injection.component;

import android.content.Context;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.google.gson.Gson;
import com.innovation.rain.app.MyApplication;
import com.innovation.rain.app.injection.module.AppCoreModule;
import com.innovation.rain.app.injection.module.AppModule;
import com.innovation.rain.app.injection.module.FeatureBuilderModule;
import com.innovation.rain.app.properties.BuildInProperties;
import com.sf0404.common.prefs.AppPreferences;
import com.sf0404.common.properties.AppProperties;
import com.sf0404.common.properties.ConstProperties;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(
        modules = {
                AppModule.class,
                AppCoreModule.class,
                FeatureBuilderModule.class
        })
public interface AppComponent {

    Gson gson();

    Context appContext();

    BuildInProperties buildInProperties();

    @Named(ConstProperties.NAMED_PROPERTY)
    AppProperties appProperties();

    @Named(RetrofitConstants.RETROFIT_SELECTED)
    Retrofit retrofit();

    AppPreferences appPreference();

    void inject(MyApplication app);
}
