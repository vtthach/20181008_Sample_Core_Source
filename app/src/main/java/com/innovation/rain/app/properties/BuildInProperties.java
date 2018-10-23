package com.innovation.rain.app.properties;

import android.support.annotation.NonNull;

import com.amb.retrofitwrapper.module.ApiMode;
import com.innovation.rain.AppConfig;
import com.sf0404.common.properties.AppProperties;

import javax.inject.Inject;

import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_MOCK_MODE;
import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_ZOOM_RATIO_ID_BOOK;
import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_ZOOM_RATIO_ID_CARD;
import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_ZOOM_RATIO_POA;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_ENDPOINT_URL;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_MOCK_METHOD;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_ZOOM_RATIO_ID_BOOK;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_ZOOM_RATIO_ID_CARD;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_ZOOM_RATIO_POA;

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

    public float getZoomRatioIdCard() {
        appProperties.reloadProperties();
        String zoomRatio = appProperties.getProperty(PROPERTY_ZOOM_RATIO_ID_CARD, DEFAULT_ZOOM_RATIO_ID_CARD);
        return Float.parseFloat(zoomRatio);
    }

    public float getZoomRatioIdBook() {
        appProperties.reloadProperties();
        String zoomRatio = appProperties.getProperty(PROPERTY_ZOOM_RATIO_ID_BOOK, DEFAULT_ZOOM_RATIO_ID_BOOK);
        return Float.parseFloat(zoomRatio);
    }

    public float getZoomRatioPOA() {
        appProperties.reloadProperties();
        String zoomRatio = appProperties.getProperty(PROPERTY_ZOOM_RATIO_POA, DEFAULT_ZOOM_RATIO_POA);
        return Float.parseFloat(zoomRatio);
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
