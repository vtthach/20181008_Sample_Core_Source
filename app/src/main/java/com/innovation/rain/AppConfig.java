package com.innovation.rain;

public class AppConfig {

    public static final boolean ENABLE_LOGGER = true; // Not for production
    public static final String END_POINT_URL = "http://tymemiddleware-uat.eu-west-1.elasticbeanstalk.com/";
    public static final boolean ENABLE_SUPPORT_MOCK_DATA = true; // Use mock data or not

    public AppConfig() {
        // Private constructor
    }
}
