package com.amb.retrofitwrapper.module;

import android.content.Context;

import com.amb.retrofitwrapper.ssl.RetrofitClientBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dmt.achilles.interceptor.FakeInterceptor;
import dmt.achilles.model.MockApiConfiguration;
import dmt.achilles.repository.AssetRepository;
import dmt.achilles.repository.ContentRepository;
import dmt.achilles.repository.ExternalStorageRepository;
import dmt.achilles.service.controller.MockBroadCastController;
import dmt.achilles.service.controller.MockContractor;
import dmt.achilles.service.controller.UserInteractConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.amb.retrofitwrapper.RetrofitConstants.CONTENT_REPOSITORY_MOCK_ASSET;
import static com.amb.retrofitwrapper.RetrofitConstants.CONTENT_REPOSITORY_MOCK_SD_CARD;
import static com.amb.retrofitwrapper.RetrofitConstants.ENABLE_LOG;
import static com.amb.retrofitwrapper.RetrofitConstants.OK_HTTP_IGNORE_CERTIFICATE;
import static com.amb.retrofitwrapper.RetrofitConstants.OK_HTTP_MOCK_ASSET;
import static com.amb.retrofitwrapper.RetrofitConstants.OK_HTTP_MOCK_SD_CARD;

@Module
public class OkHttpModule {

    Context context;
    private String mockSdCardDirectory;
    private int timeoutInMinute;

    public OkHttpModule(Context applicationContext, String directory, int timeoutInMinute) {
        this.context = applicationContext;
        this.mockSdCardDirectory = directory;
        this.timeoutInMinute = timeoutInMinute;
    }

    @Provides
    @Singleton
    @Named(OK_HTTP_IGNORE_CERTIFICATE)
    OkHttpClient provideOkHttpClient(@Named(ENABLE_LOG) boolean enableLog) {
        RetrofitClientBuilder builder = new RetrofitClientBuilder()
                .ignoreCertificates(timeoutInMinute);
        if (enableLog) {
            builder.log(HttpLoggingInterceptor.Level.BODY);
        }
        return builder.build();
    }

    @Provides
    @Singleton
    @Named(OK_HTTP_MOCK_ASSET)
    OkHttpClient provideMockAssetOkHttpClient(@Named(CONTENT_REPOSITORY_MOCK_ASSET) ContentRepository assetContentRepository, MockContractor mockController) {
        return new OkHttpClient.Builder().addInterceptor(new FakeInterceptor(assetContentRepository, mockController)).build();
    }

    @Provides
    @Singleton
    @Named(OK_HTTP_MOCK_SD_CARD)
    OkHttpClient provideMockSdCardOkHttpClient(@Named(CONTENT_REPOSITORY_MOCK_SD_CARD) ContentRepository sdcardContentRepository, MockContractor mockController) {
        return new OkHttpClient.Builder().addInterceptor(new FakeInterceptor(sdcardContentRepository, mockController)).build();
    }

    @Provides
    @Singleton
    @Named(CONTENT_REPOSITORY_MOCK_SD_CARD)
    ContentRepository provideSdCardContentRepository(Context context, MockApiConfiguration mockApiConfiguration) {
        return new ExternalStorageRepository(context, mockApiConfiguration, mockSdCardDirectory);
    }

    @Provides
    @Singleton
    @Named(CONTENT_REPOSITORY_MOCK_ASSET)
    ContentRepository provideContentRepository(Context context, MockApiConfiguration mockApiConfiguration) {
        return new AssetRepository(context, mockApiConfiguration);
    }

    @Provides
    @Singleton
    MockContractor provideMockController(Context context, UserInteractConfig config) {
        return new MockBroadCastController(context, config);
    }

    @Provides
    @Singleton
    UserInteractConfig provideUserInteractConfig() {
        return () -> true;
    }

    @Provides
    @Singleton
    MockApiConfiguration provideMockApiConfig() {
        // create default mock configuration
        return new MockApiConfiguration.Buider()
                .endpointConfigPath("mock_api/endpointconfigData.json")
                .apiPath("mock_api/api-config")
                .scenarioPath("mock_api/scenarios")
                .scenarioName("All_Success_Flows")
                .build();
    }
}
