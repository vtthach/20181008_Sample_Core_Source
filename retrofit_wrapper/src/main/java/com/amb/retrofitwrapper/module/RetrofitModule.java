package com.amb.retrofitwrapper.module;


import com.amb.retrofitwrapper.RetrofitConstants;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.amb.retrofitwrapper.RetrofitConstants.RETROFIT_IGNORE_CERTIFICATE;
import static com.amb.retrofitwrapper.RetrofitConstants.RETROFIT_MOCK_ASSET;
import static com.amb.retrofitwrapper.RetrofitConstants.RETROFIT_MOCK_SD_CARD;
import static com.amb.retrofitwrapper.RetrofitConstants.RETROFIT_SELECTED;

@Module
public class RetrofitModule {

    protected final String baseUrl;
    private final ApiMode apiMode;

    public RetrofitModule(String baseUrl, ApiMode apiMode) {
        this.baseUrl = baseUrl;
        this.apiMode = apiMode;
    }

    @Provides
    @Singleton
    @Named(RETROFIT_IGNORE_CERTIFICATE)
    Retrofit provideRetrofit(Gson gson,
                             @Named(RetrofitConstants.OK_HTTP_IGNORE_CERTIFICATE) OkHttpClient okHttpClient) {
        return build(gson, okHttpClient);
    }

    private Retrofit build(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.RETROFIT_MOCK_ASSET)
    Retrofit provideRetrofitMock(Gson gson,
                                 @Named(RetrofitConstants.OK_HTTP_MOCK_ASSET) OkHttpClient okHttpClient) {
        return build(gson, okHttpClient);
    }

    @Provides
    @Singleton
    @Named(RetrofitConstants.RETROFIT_MOCK_SD_CARD)
    Retrofit provideRetrofitSdCard(Gson gson,
                                   @Named(RetrofitConstants.OK_HTTP_MOCK_SD_CARD) OkHttpClient okHttpClient) {
        return build(gson, okHttpClient);
    }

    @Provides
    @Singleton
    @Named(RETROFIT_SELECTED)
    public Retrofit provideRetrofitSelected(@Named(RETROFIT_IGNORE_CERTIFICATE) Retrofit retrofitIgnoreCer,
                                            @Named(RETROFIT_MOCK_ASSET) Retrofit retrofitMockAsset,
                                            @Named(RETROFIT_MOCK_SD_CARD) Retrofit retrofitMockSdCard) {
        switch (apiMode) {
            case ASSET:
                return retrofitMockAsset;
            case SD_CARD:
                return retrofitMockSdCard;
            case REAL:
            default:
                return retrofitIgnoreCer;
        }
    }
}
