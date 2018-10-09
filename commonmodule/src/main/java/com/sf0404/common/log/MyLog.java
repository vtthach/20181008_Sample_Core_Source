package com.sf0404.common.log;

import timber.log.Timber;

public class MyLog {

    private static final String MY_LOG_TAG = "vtt";

    public MyLog() {
    }

    public static void logI(String msg) {
        logI("", msg);
    }

    public static void logI(String tag, String msg) {
        Timber.i("[%s] %s-%s %s", getCurrentThreadName(),  MY_LOG_TAG, tag, msg);
    }

    private static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    public static void logE(Throwable e, String tag, String message) {
        Timber.e(e, "[%s] %s-%s %s", getCurrentThreadName(), MY_LOG_TAG, tag, message);
    }

    public static void logE(String tag, String message) {
        logE((Throwable) null, tag, message);
    }

    public static void logE(String message) {
        logE("", message);
    }

    public static void logD(InterruptedException e, String tag, String message) {
        Timber.d(e, "[%s] %s-%s %s", getCurrentThreadName(), MY_LOG_TAG, tag, message);
    }
}
