package com.sf0404.common.utils;

import android.telephony.PhoneNumberUtils;

public class ValidationUtils {
    public final static boolean isValidEmail(String target) {
        if (isNullOrEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isNullOrEmpty(String target) {
        return target == null || target.isEmpty();
    }

    public static boolean isValidPhoneNumber(String input) {
        return PhoneNumberUtils.isGlobalPhoneNumber(input);
    }
}
