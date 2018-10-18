package com.innovation.rain.app.properties;

import android.support.annotation.NonNull;

import com.amb.retrofitwrapper.module.ApiMode;
import com.innovation.rain.AppConfig;
import com.sf0404.common.properties.AppProperties;

import javax.inject.Inject;

import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_MOCK_MODE;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_ENDPOINT_URL;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_MOCK_METHOD;

public class BuildInProperties {

    private AppProperties appProperties;

    @Inject
    public BuildInProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public ApiMode getApiMode() {
        appProperties.reloadProperties();
        String mockMode = appProperties.getProperty(PROPERTY_MOCK_METHOD, DEFAULT_MOCK_MODE);
        return ApiMode.getTypeFromId(mockMode);
    }

    public String getHostUrl() {
        appProperties.reloadProperties();
        String url = appProperties.getProperty(PROPERTY_ENDPOINT_URL, AppConfig.END_POINT_URL);
        return getValidEndPointUrl(url);
    }

    private String getValidEndPointUrl(String endpoint) {
        if (endpoint == null) {
            endpoint = "";
        }
        if (isNotEndWithFlash(endpoint)) {
            endpoint = endpoint + "/";
        }
        return endpoint;
    }

    private boolean isNotEndWithFlash(@NonNull String endpoint) {
        return !endpoint.endsWith("/");
    }

}
