package com.sf0404.common.utils;


import android.support.annotation.NonNull;

public class ConvertUtils {
    public static double getDouble(@NonNull String string) {
        try {
            return Double.valueOf(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int safeGetInt(String productId, int defaultValue) {
        try {
            return Integer.valueOf(productId);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
