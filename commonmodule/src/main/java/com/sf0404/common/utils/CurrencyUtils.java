package com.sf0404.common.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {
    public static String getFormatMoneyGerman(double totalPayment, String currentcy) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        nf.setGroupingUsed(true);
        return nf.format(totalPayment) + " " + currentcy;
    }
}
