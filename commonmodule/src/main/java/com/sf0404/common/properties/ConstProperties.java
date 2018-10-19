package com.sf0404.common.properties;


public class ConstProperties {

    static final String NAMED_PROPERTY_FILE_PATH = "NAMED_PROPERTY_FILE_PATH";
    static final String NAMED_PROPERTY_ASSET_PATH = "NAMED_PROPERTY_ASSET_PATH";
    static final String NAMED_AUTOMATION_PROPERTY_FILE_PATH = "NAMED_AUTOMATION_PROPERTY_FILE_PATH";
    static final String NAMED_AUTOMATION_PROPERTY_ASSET_PATH = "NAMED_AUTOMATION_PROPERTY_ASSET_PATH";

    public static final String NAMED_PROPERTY = "NAMED_PROPERTY";
    public static final String NAMED_AUTOMATION_PROPERTY = "NAMED_AUTOMATION_PROPERTY";

    private ConstProperties() {
        // Private constructor
    }

    /**
     * Key used to get from property
     */
    public static final class Key {
        public static final String PROPERTY_ENDPOINT_URL = "endpointUrl";
        public static final String PROPERTY_MOCK_METHOD = "mockMethod";
        static final String PROPERTY_ENABLE_TIMEOUT_SERVICE = "enable_timeout_service";

        static final String PROPERTY_SHOPPER_CARD_EXPOSURE_VALUE = "shopper_card_exposure_value";
        static final String PROPERTY_SHOPPER_CARD_CONTRAST_VALUE = "shopper_card_contrast_value";

        // cellphone
        static final String PROPERTY_CELLPHONE_LENGTH = "cellphone_length";
        static final String PROPERTY_CELLPHONE_PATTERN = "cellphone_pattern";

        // printer
        static final String PROPERTY_IP_PRINTER = "ip_printer";
        static final String PROPERTY_CARD_OUT_DISPENSER_LENGTH = "card_out_dispenser_length";
        static final String PROPERTY_TIMEOUT_GOT_CARD = "timeout_got_card";

        // Barcode scanner
        static final String PROPERTY_BARCODE_SCANNER_IP_ADDRESS = "barcode_scanner_ip_address";
        static final String PROPERTY_BARCODE_PORT = "barcode_port";
        static final String PROPERTY_BARCODE_SOCKET_TIMEOUT_IN_MILLIS = "barcode_socket_timeout_in_millis";
        static final String PROPERTY_BARCODE_TRY_NUMBER_TIME = "barcode_try_number_time";

        // id document
        public static final String PROPERTY_ZOOM_RATIO_ID_CARD = "zoomRatioIdCard";
        public static final String PROPERTY_ZOOM_RATIO_ID_BOOK = "zoomRatioIdBook";
        public static final String PROPERTY_OUT_THRESHOLD_ID_BOOK = "outThresholdIdBook";
        public static final String PROPERTY_IN_THRESHOLD_ID_BOOK = "inThresholdIdBook";
        public static final String PROPERTY_OUT_THRESHOLD_ID_CARD = "outThresholdIdCard";
        public static final String PROPERTY_IN_THRESHOLD_ID_CARD = "inThresholdIdCard";
        public static final String PROPERTY_MIN_DOCUMENT_RATIO = "minDocumentRatio";
        public static final String PROPERTY_MAX_DOCUMENT_RATIO = "maxDocumentRatio";
        public static final String PROPERTY_SCALE_DPI_COLOR_DETECTION = "scaleDpiColorDetection";
        static final String PROPERTY_ID_CARD_MIN_FACE = "id_card_min_face";
        static final String PROPERTY_ID_CARD_ZOOM = "id_card_zoom";
        static final String PROPERTY_ID_BOOK_ZOOM = "id_book_zoom";
        static final String PROPERTY_ID_BOOK_DETECT_RESULT = "id_book_detect_result";
        static final String PROPERTY_ID_BOOK_CARD_SHOW_DEBUG = "id_book_card_show_debug";

        // selfie
        static final String PROPERTY_ENABLE_ENROLL_USB = "enable_enroll_usb";
        static final String PROPERTY_ENROLL_USB_ID = "enroll_usb_id";

        // liveness check
        static final String PROPERTY_LIVENESS_MIN_EYE_DISTANCE_RATIO = "liveness_min_eye_distance_ratio";
        static final String PROPERTY_LIVENESS_MAX_EYE_DISTANCE_RATIO = "liveness_max_eye_distance_ratio";
        static final String PROPERTY_LIVENESS_PROXIMITY_PERCENT = "liveness_proximity_percent";

        // automation
        static final String PROPERTY_ID_SIT_VN_TESTING = "sit_vn_testing";
        static final String PROPERTY_AUTO_SSC_PATH = "ssc_path";
        static final String PROPERTY_AUTO_USER_PICTURE_PATH = "user_picture_path";
        static final String PROPERTY_AUTO_POR_PATH = "por_path";
        static final String PROPERTY_AUTO_EXECUTE_CARD_RESULT = "printer_execute_card_result";
        static final String PROPERTY_AUTO_CARD_STATUS = "printer_card_status";
        static final String PROPERTY_AUTO_BARCODE_SCANNER_CONNECTED = "barcode_scanner_connected";
        static final String PROPERTY_AUTO_CARD_UID = "card_uid";
        static final String PROPERTY_AUTO_FACETECH_LIVENESS = "liveness_status";
        static final String PROPERTY_ENROLL_STATUS_CODE = "enroll_status";
        static final String PROPERTY_AUTO_USB_CAMERA_CONNECTED = "usb_camera_connected";

        static final String PROPERTY_ENABLE_PERSONAL_LOAN = "enable_personal_loan";
        static final String PROPERTY_ENABLE_MOCK_USER_INTERACT = "mock_user_interact";
        static final String PROPERTY_DISABLE_FACIAL_RECOGNITION = "disable_facial_recognition";

        private Key() {
            // Private constructor
        }
    }
}
