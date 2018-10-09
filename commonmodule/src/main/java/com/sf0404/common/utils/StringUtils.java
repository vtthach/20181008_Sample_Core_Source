package com.sf0404.common.utils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(StringBuilder str) {
        return str == null || str.length() <= 0;
    }
}
