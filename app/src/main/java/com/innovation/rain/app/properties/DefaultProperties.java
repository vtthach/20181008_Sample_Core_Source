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
    static final String DEFAULT_ZOOM_RATIO_ID_CARD = "1.5";
    static final String DEFAULT_ZOOM_RATIO_ID_BOOK = "1.2";
    static final String DEFAULT_ZOOM_RATIO_POA = "1";

    private DefaultProperties() {
        // Private constructor
    }
}
