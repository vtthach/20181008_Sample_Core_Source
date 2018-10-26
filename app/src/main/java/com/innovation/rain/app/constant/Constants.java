package com.innovation.rain.app.constant;

public class Constants {
    private Constants() {
    }

    public static final int MAX_ATTEMPTS = 3;

    public static class KioskController {
        public static final String PACKAGE_NAME = "com.cbsa.kiosk.controller";
        public static final String ACTION = PACKAGE_NAME + ".action.register_package";
        public static final String EXTRA = "com.cbsa.kiosk.controller.extra.package_name";

        private KioskController() {
            // Private constructor
        }
    }

    public static class LoggerSetting {

        public static final long MAX_LOG_FILE_SIZE = (long) 5 * 1024 * 1024;
        public static final String API_LOG_FILE_PATH = CachedPath.LOG_PATH_BASE + "/Api";

        /**
         * Hide default constructor
         */
        private LoggerSetting() {
        }
    }

    public class ApiConfig {
        public static final int TIMEOUT_IN_MINUTE = 2;

        /**
         * Hide default constructor
         */
        private ApiConfig() {
        }
    }
}

