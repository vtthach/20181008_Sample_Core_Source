package com.innovation.rain.app.logger;

import okhttp3.logging.HttpLoggingInterceptor.Logger;
import timber.log.Timber;


public class NetworkLoggerImpl extends FileLogger implements Logger {

    public NetworkLoggerImpl(KioskLoggerConfig networkLoggerConfig) {
        super(networkLoggerConfig.getFilePath(), networkLoggerConfig.getMaxLogFileSize(), networkLoggerConfig.isShouldWriteLog());
    }

    @Override
    public void log(String message) {
        Timber.i(message);
        if (isShouldWriteLog())
            i(null, message);
    }
}
