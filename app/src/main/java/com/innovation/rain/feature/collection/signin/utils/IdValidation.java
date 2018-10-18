package com.innovation.rain.feature.collection.signin.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

public class IdValidation {
    /**
     * Validate date boolean.
     *
     * @param date   the date
     * @param format the format
     * @return the boolean
     */
    public static boolean validateDate(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.set2DigitYearStart(new GregorianCalendar(1997, 1, 1).getTime());
        try {
            //Get MMdd string
            String sMonthDay = date.substring(2, date.length());
            //Get yyyy year after converting from yy year
            String year = new SimpleDateFormat("yyyy").format(formatter.parse(date));

            //Checking if it is valid date
            SimpleDateFormat standardFormatter = new SimpleDateFormat("yyyyMMdd");
            standardFormatter.setLenient(false);
            standardFormatter.parse(year + sMonthDay);
        } catch (ParseException e) {
            Timber.e("validateDate", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Check luhn digit int.
     *
     * @param input the input
     * @return the int
     */
    private static int checkLuhnDigit(String input) {
        int total = 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            int multiple = (count % 2) + 1;
            count++;
            double temp = (double) multiple * Integer.parseInt(String.valueOf(input.charAt(i)));
            temp = (int) Math.floor(temp / 10) + (temp % 10);
            total += temp;
        }

        total = (total * 9) % 10;

        return total;
    }

    /**
     * Validate id number boolean.
     *
     * @param idNumber the id number
     * @return the boolean
     */
    public static boolean validateIdNumber(String idNumber) {
        try {
            //check if idNumber must contain 13 digit
            Pattern pattern = Pattern.compile("[0-9]{13}");
            Matcher matcher = pattern.matcher(idNumber);

            if (!matcher.matches()) {
                return false;
            }
            //check if the 11th digit is 0, 1 or2
            pattern = Pattern.compile("[0-2]");
            matcher = pattern.matcher(idNumber.substring(10, 11));
            if (!matcher.matches()) {
                return false;
            }

            //check if 6 first digits must be yyMMdd date format
            if (!validateDate(idNumber.substring(0, 6), "yyMMdd")) {
                return false;
            }

            //Check sum
            int lastNumber = Integer.parseInt(String.valueOf(idNumber.charAt(idNumber.length() - 1)));
            String numberSection = idNumber.substring(0, idNumber.length() - 1);

            return lastNumber == checkLuhnDigit(numberSection);
        } catch (Exception e) {
            Log.e("validateIdNumber", "Exception e " + e);
            return false;
        }
    }
}
