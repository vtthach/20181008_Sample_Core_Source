package com.innovation.rain.app.logger;


public interface KioskLoggerConfig {
    String getFilePath();

    long getMaxLogFileSize();

    boolean isShouldWriteLog();
}
