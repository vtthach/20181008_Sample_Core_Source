package com.sf0404.common.utils;


import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class DateTimeUtils {
    public static final String FORMAT_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMAT_HEADER = "dd MMM yyyy";

    public static final String DATE_FORMAT = "EEEE dd/MM/yyyy";
    public static final String FORMAT_TIME_ONLY = "h:mm a";
    public static final String FORMAT_DATE_TIME = "h:mm a - dd/MM/yyyy";
    public static final String FORMAT_TIME_DURATION = "h'h' mm''";
    private static final String FORMAT_TIME_DURATION_UNDER_1_HOUR = "mm'm'";

    public static String convertTime(double utcValue, String formatDisplay) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDisplay, Locale.getDefault());
            return simpleDateFormat.format((long) (utcValue * 1000));
        } catch (IllegalArgumentException e) {
            Timber.e("convertTime error: " + utcValue);
            return "";
        }
    }

    private static String getTimeFormat(long utcValue, String formatDisplay) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDisplay, Locale.getDefault());
            return simpleDateFormat.format(utcValue);
        } catch (IllegalArgumentException e) {
            Timber.e("convertTime error: " + utcValue);
            return "";
        }
    }

    public static String getTimeFormat(long utcValue) {
        return getTimeFormat(utcValue, DATE_FORMAT);
    }

    public static long getNextDate(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, date);
        return calendar.getTimeInMillis();
    }

    /**
     * @param time
     * @param strToday
     * @param strYesterday
     * @return
     */
    public static String getDisplayTime(long time, String strToday, String strYesterday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        if (isToday(calendar, today)) {
            return strToday;
        } else if (isToday(calendar, yesterday)) {
            return strYesterday;
        } else {
            return getDateWithFormat(FORMAT_HEADER, time);
        }
    }

    private static boolean isToday(Calendar timeToCheck, Calendar today) {
        return timeToCheck.get(Calendar.YEAR) == today.get(Calendar.YEAR) && timeToCheck.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR);
    }

    public static String convertTime(double utcValue) {
        return convertTime(utcValue, DATE_FORMAT);
    }

    public static double getTime() {
        return System.currentTimeMillis() / 1000d;
    }

    public static String getRelativeTimeSpanString(String strTime) {
        long time = getMilisFromString(strTime);
        return TimeAgo.toDuration(System.currentTimeMillis() - time);
    }

    private static long getMilisFromString(String strTime) {
        try {
            if (!TextUtils.isEmpty(strTime)) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sf = new SimpleDateFormat(FORMAT_TIME, Locale.getDefault());
                sf.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date date = sf.parse(strTime);
                cal.setTime(date);
                return cal.getTimeInMillis();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getStringCurrentDate(String formatTime) {
        SimpleDateFormat sf = new SimpleDateFormat(formatTime, Locale.getDefault());
        sf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sf.format(Calendar.getInstance().getTime());
    }

    public static String getTimeOnly(long time) {
        return getDateWithFormat(FORMAT_TIME_ONLY, time);
    }

    public static String getTimeDuration(long time) {
        String format = time <= TimeUnit.HOURS.toMillis(1)
                ? FORMAT_TIME_DURATION_UNDER_1_HOUR
                : FORMAT_TIME_DURATION;
        return getDateWithFormat(format, time);
    }

    public static String getTimeWithCheckToday(Calendar calendar, String dateFormat, String stringToday) {
        Calendar today = Calendar.getInstance();
        String timeStr = getDateWithFormat(dateFormat, calendar.getTimeInMillis());
        return isToday(calendar, today) ? stringToday + " - " + timeStr : timeStr;
    }

    public static class TimeAgo {
        public static final List<Long> times = Arrays.asList(
                TimeUnit.DAYS.toMillis(365),
                TimeUnit.DAYS.toMillis(30),
                TimeUnit.DAYS.toMillis(7),
                TimeUnit.DAYS.toMillis(1),
                TimeUnit.HOURS.toMillis(1),
                TimeUnit.MINUTES.toMillis(1),
                TimeUnit.SECONDS.toMillis(1));

        public static final List<String> timesString = Arrays.asList("year", "month", "week", "day", "hour", "minute", "sec");

        public static String toDuration(long duration) {

            StringBuffer res = new StringBuffer();
            for (int i = 0; i < times.size(); i++) {
                Long current = times.get(i);
                long temp = duration / current;
                if (temp > 0) {
                    // i != 6  -> 6 is index of "sec"
                    res.append(temp).append(" ").append(timesString.get(i)).append(temp > 1 && i != 6 ? "s" : "").append(" ago");
                    break;
                }
            }
            if ("".equals(res.toString()))
                return "Just now";
            else
                return res.toString();
        }
    }

    public static Date getDateFromString(String strDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_TIME, Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            return formatter.parse(strDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * Compare two date with default format FORMAT_TIME
     *
     * @param date1
     * @param date2
     * @return an int < 0 if this date1 is less than the date2, 0 if they are equal, and an int > 0 if this date1 is greater.
     */
    public static int compareDate(String date1, String date2) {
        if (TextUtils.isEmpty(date1) || TextUtils.isEmpty(date2)) {
            return 0;
        }
        Date dateOne = getDateFromString(date1);
        Date dateTwo = getDateFromString(date2);
        return dateOne.compareTo(dateTwo);
    }

    private static String getTimeReadable(int minutes) {
        String finalStrExpiry = minutes + "min";
        if (minutes >= 60) {
            int hour = minutes / 60;
            int min = minutes % 60;
            finalStrExpiry = hour + "h " + min + "min";
        }
        return finalStrExpiry;
    }

    public static String getDateWithFormat(String format, long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(time);
    }

}
