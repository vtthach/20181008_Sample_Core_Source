package com.innovation.rain.app.logger;

import android.content.Context;

import com.innovation.rain.AppConfig;
import com.innovation.rain.app.constant.Constants;

/**
 * Project:  cbsa
 * Author:   Khuong Vo
 * Since:    4/28/2016
 * Time:     6:21 PM
 */
public class KioskLoggerConfigImpl implements KioskLoggerConfig {

    public Context context;
    private String logFilePath;

    public KioskLoggerConfigImpl(Context context, String logFilePath) {
        this.context = context;
        this.logFilePath = logFilePath;
    }

    @Override
    public boolean isShouldWriteLog() {
        return AppConfig.ENABLE_LOGGER;
    }

    @Override
    public long getMaxLogFileSize() {
        return Constants.LoggerSetting.MAX_LOG_FILE_SIZE;
    }

    @Override
    public String getFilePath() {
        return logFilePath;
    }
}
