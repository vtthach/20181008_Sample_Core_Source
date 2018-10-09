package com.innovation.rain.app.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.sf0404.common.utils.FileUtils.writeStringToFileAutoSplit;

public class FileLogger implements MyLogger {

    private static final String FORMAT_DATE_LOG_ITEM = "yyyy-MM-dd:HH:mm:ss,SSS";
    private static final String FORMAT_DATE_LOG_FILE = "yyyy-MM-dd";
    private static final String DEBUG = "DEBUG";
    private static final String INFO = "INFO";
    private static final String ERROR = "ERROR";

    private String logFolderPath;
    private long maxLogFileSize;
    private boolean shouldWriteLog;

    public FileLogger(String logFolderPath, long maxLogFileSize, boolean shouldWriteLog) {
        this.logFolderPath = logFolderPath;
        this.maxLogFileSize = maxLogFileSize;
        this.shouldWriteLog = shouldWriteLog;
    }

    protected boolean isShouldWriteLog() {
        return shouldWriteLog;
    }

    @Override
    public void d(String key, String value) {
        if (shouldWriteLog) {
            String message = getLogMessage(key, value, "%s:%s");
            writeDebugMessage(message);
        }
    }

    @Override
    public void i(String key, String value) {
        if (shouldWriteLog) {
            String message = getLogMessage(key, value, "%s:%s");
            writeInfoMessage(message);
        }
    }

    @Override
    public void e(String key, String value) {
        if (shouldWriteLog) {
            String message = getLogMessage(key, value, "%s:%s");
            writeErrorMessage(message);
        }
    }

    protected void writeDebugMessage(String message) {
        synchronized (FileLogger.class) {
            writeStringToFileAutoSplit(getCurrentFilePath(), getFormatMessage(DEBUG, message), maxLogFileSize);
        }
    }

    protected void writeInfoMessage(String message) {
        synchronized (FileLogger.class) {
            writeStringToFileAutoSplit(getCurrentFilePath(), getFormatMessage(INFO, message), maxLogFileSize);
        }
    }

    protected void writeErrorMessage(String message) {
        synchronized (FileLogger.class) {
            writeStringToFileAutoSplit(getCurrentFilePath(), getFormatMessage(ERROR, message), maxLogFileSize);
        }
    }

    private String getFormatMessage(String type, String originMsg) {
        return String.format("[%s %s] %s", getDateTimeText(FORMAT_DATE_LOG_ITEM), type, originMsg);
    }

    private String getCurrentFilePath() {
        return logFolderPath + "/" + getDateTimeText(FORMAT_DATE_LOG_FILE) + ".log";
    }

    public String getLogMessage(String key, String value, String format) {
        return String.format(format, key != null ? key + " " + getTag() : getTag(), value);
    }

    public String getTag() {
        return getDateTimeText(FORMAT_DATE_LOG_FILE);
    }

    public String getDateTimeText(String formatDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.getDefault());
        return sdf.format(new Date());
    }
}
