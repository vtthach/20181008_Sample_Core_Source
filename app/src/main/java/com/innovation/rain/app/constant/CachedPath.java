package com.innovation.rain.app.constant;


import android.os.Environment;

import java.io.File;

import static com.innovation.rain.app.constant.CachedPath.Name.APP_PARENT_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.AUTOMATION_FOLDER_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.AUTOMATION_PROPERTIES_FILE_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.DATA_APP_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.LOG_FOLDER_API_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.LOG_FOLDER_APP_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.LOG_FOLDER_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.MOCK_DATA_FOLDER_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.PROPERTIES_FILE_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.PROPERTIES_FOLDER_NAME;
import static com.innovation.rain.app.constant.CachedPath.Name.TNC_NAME;


public class CachedPath {

    private static final char CHAR_SEP = File.separatorChar;
    // Base folder path on sdcard
    private static final String APP_PARENT_PATH = Environment.getExternalStorageDirectory().getPath() + CHAR_SEP + APP_PARENT_NAME;
    // properties/application.properties
    public static final String PROPERTIES_ASSET_PATH = PROPERTIES_FOLDER_NAME + CHAR_SEP + PROPERTIES_FILE_NAME;
    // sdcard/AppName/properties
    public static final String PROPERTIES_FOLDER_PATH = APP_PARENT_PATH + CHAR_SEP + PROPERTIES_FOLDER_NAME;
    // sdcard/AppName/properties/application.properties
    public static final String PROPERTIES_FILE_PATH = PROPERTIES_FOLDER_PATH + CHAR_SEP + PROPERTIES_FILE_NAME;
    // sdcard/AppName/mock
    public static final String MOCK_SD_CARD_FOLDER_PATH = APP_PARENT_PATH + CHAR_SEP + MOCK_DATA_FOLDER_NAME;
    // properties/automation.properties
    public static final String AUTOMATION_PROPERTIES_ASSET_PATH = PROPERTIES_FOLDER_NAME + CHAR_SEP + AUTOMATION_PROPERTIES_FILE_NAME;
    // sdcard/AppName/properties/
    public static final String AUTOMATION_PROPERTIES_FOLDER_PATH = APP_PARENT_PATH + CHAR_SEP + PROPERTIES_FOLDER_NAME;
    // sdcard/AppName/properties/automation.properties
    public static final String AUTOMATION_PROPERTIES_FILE_PATH = AUTOMATION_PROPERTIES_FOLDER_PATH + CHAR_SEP + AUTOMATION_PROPERTIES_FILE_NAME;
    public static final String AUTOMATION_FOLDER_PATH = APP_PARENT_PATH + CHAR_SEP + AUTOMATION_FOLDER_NAME + CHAR_SEP;
    // sdcard/AppName/properties/log
    public static final String LOG_PATH_BASE = APP_PARENT_PATH + CHAR_SEP + LOG_FOLDER_NAME;
    public static final String LOG_PATH_API = LOG_PATH_BASE + CHAR_SEP + LOG_FOLDER_API_NAME;
    public static final String LOG_PATH_APP = LOG_PATH_BASE + CHAR_SEP + LOG_FOLDER_APP_NAME;
    // sdcard/AppName/data/terms_and_conditions
    public static final String KIOSK_TNC_FOLDER_PATH = APP_PARENT_PATH + CHAR_SEP + DATA_APP_NAME + CHAR_SEP + TNC_NAME;

    private CachedPath() {
        // Private constructor
    }

    static final class Name {
        static final String APP_PARENT_NAME = "rain";
        static final String PROPERTIES_FILE_NAME = "application.properties";
        static final String AUTOMATION_PROPERTIES_FILE_NAME = "automation.properties";
        static final String PROPERTIES_FOLDER_NAME = "properties";
        static final String MOCK_DATA_FOLDER_NAME = "mock_data";
        static final String AUTOMATION_FOLDER_NAME = "automation";
        static final String LOG_FOLDER_NAME = "log";
        static final String LOG_FOLDER_API_NAME = "api";
        static final String LOG_FOLDER_APP_NAME = "app";
        static final String DATA_APP_NAME = "data";
        static final String TNC_NAME = "terms_and_conditions";

        private Name() {
            // Private constructor
        }
    }
}
