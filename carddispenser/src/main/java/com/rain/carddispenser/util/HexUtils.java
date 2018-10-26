package com.rain.carddispenser.util;

public class HexUtils {
    public static String intToHex(int value) {
        char hex[] = {
                Character.forDigit((value >> 4) & 0x0F, 16),
                Character.forDigit(value & 0x0F, 16)
        };
        return new String(hex);
    }
}
