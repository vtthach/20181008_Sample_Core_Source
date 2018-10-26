package com.innovation.rain.app.constant;

public class ApiConstants {
    public class Url {
        public static final String CLIENT_LOGIN = "api/clientlogin";
        public static final String SIM_CARD_DISPENSE = "/api/Kiosks/simcard/dispense";
        public static final String AGENT_LOGIN = "api/profiles/agent/login";
    }

    public class ErrorCode {
        public static final String NO_ORDER = "400"; // Not order associate with id
        public static final String NON_REGISTER = "404"; // Not found Id
        public static final String ALREADY_LOGIN = "9001";
        public static final String AGENT_NOT_FOUND = "2000";
    }
}
