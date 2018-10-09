package com.innovation.rain.app.properties;

import com.amb.retrofitwrapper.module.ApiMode;
import com.innovation.rain.AppConfig;

public class DefaultProperties {
    /**
     * Default property values
     * Normally not used these values of this class if we've already defined data in asset file
     */
    static final String DEFAULT_ENDPOINT = AppConfig.END_POINT_URL;
    static final String DEFAULT_MOCK_MODE = ApiMode.DEFAULT.getId();

    private DefaultProperties() {
        // Private constructor
    }
}
