package com.innovation.rain.app.properties;

import com.amb.retrofitwrapper.module.ApiMode;
import com.sf0404.common.properties.AppProperties;

import javax.inject.Inject;

import static com.innovation.rain.app.properties.DefaultProperties.DEFAULT_MOCK_MODE;
import static com.sf0404.common.properties.ConstProperties.Key.PROPERTY_MOCK_METHOD;

public class BuildInProperties {

    private AppProperties mAppProperties;

    @Inject
    public BuildInProperties(AppProperties appProperties) {
        this.mAppProperties = appProperties;
    }

    public ApiMode getMockMethod() {
        String mockMode = mAppProperties.getProperty(PROPERTY_MOCK_METHOD, DEFAULT_MOCK_MODE);
        return ApiMode.getTypeFromId(mockMode);
    }
}
