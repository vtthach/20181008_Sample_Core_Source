package com.innovation.rain.app.constant;

public class ApiConstants {
    public class Url {
        public static final String CLIENT_LOGIN = "api/clientlogin";
        public static final String SIM_CARD_DISPENSE = "/api/Kiosks/simcard/dispense";
    }

    public class ErrorCode {
        public static final String NO_ORDER = "400"; // Not order associate with id
        public static final String NON_REGISTER = "404"; // Not found Id
    }
}
