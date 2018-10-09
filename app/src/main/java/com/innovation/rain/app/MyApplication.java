package com.innovation.rain.app;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.amb.retrofitwrapper.module.ApiMode;
import com.amb.retrofitwrapper.module.OkHttpModule;
import com.amb.retrofitwrapper.module.RetrofitModule;
import com.crashlytics.android.Crashlytics;
import com.innovation.rain.AppConfig;
import com.innovation.rain.BuildConfig;
import com.innovation.rain.app.appstatic.AppStaticDirectoryImpl;
import com.innovation.rain.app.constant.CachedPath;
import com.innovation.rain.app.constant.Constants;
import com.innovation.rain.app.injection.component.AppComponent;
import com.innovation.rain.app.injection.component.DaggerAppComponent;
import com.innovation.rain.app.injection.module.AppModule;
import com.innovation.rain.app.logger.FileLogger;
import com.innovation.rain.app.logger.KioskLoggerConfig;
import com.innovation.rain.app.logger.KioskLoggerConfigImpl;
import com.innovation.rain.app.properties.BuildInProperties;

import java.util.Arrays;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;


/**
 * The type Kiosk application.
 */
public class MyApplication extends Application implements HasSupportFragmentInjector, HasServiceInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceInjector;

    @Inject
    BuildInProperties buildInProperties;

    static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static MyApplication getInstance() {
        return ContextSingleton.getContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();

        ContextSingleton.setContext(this);

        initAppPermission(this);

        initializeLogger();

        // initialize app default folder
        initializeDirectories();

        // initialize app property
        initializeAppProperties();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .retrofitModule(getRetrofitModule(AppConfig.END_POINT_URL))
                .okHttpModule(getOkHttpModule())
                .build();
        appComponent.inject(this);
    }

    protected OkHttpModule getOkHttpModule() {
        return new OkHttpModule(getApplicationContext(),
                CachedPath.MOCK_SD_CARD_FOLDER_PATH,
               Constants.ApiConfig.TIMEOUT_IN_MINUTE);
    }

    protected RetrofitModule getRetrofitModule(String hostUrl) {
        return new RetrofitModule(hostUrl, getApiMode());
    }

    private ApiMode getApiMode() {
        return buildInProperties.getMockMethod();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceInjector;
    }

    private void initializeDirectories() {
        new AppStaticDirectoryImpl(getApplicationContext()).checking();
    }

    private void initializeAppProperties() {
//        mAppComponent.appProperties().reloadProperties();
//        mAppComponent.automationProperties().reloadProperties();
    }

    //@Override
    protected void log(int priority, String tag, String message) {
        Crashlytics.log(priority, tag, message);
    }

    //@Override
    protected void logException(Throwable t) {
        Crashlytics.logException(t);
    }

    protected KioskLoggerConfig getAppLoggerConfig() {
        return new KioskLoggerConfigImpl(this, CachedPath.LOG_PATH_APP);
    }

    private void initAppPermission(Context context) {
        Intent intent = new Intent(Constants.KioskController.ACTION);
        intent.setPackage(Constants.KioskController.PACKAGE_NAME);
        intent.putExtra(Constants.KioskController.EXTRA, context.getPackageName());
        try {
            context.startService(intent);
        } catch (Exception e) {
            Timber.e(e, "Something went wrong when start KioskControllerService: " + e.getMessage());
        }
    }

    private void initializeLogger() {
        if (BuildConfig.DEBUG) {
            // Enable log for logcat
            Timber.plant(new Timber.DebugTree());
        } else {
            // For release mode
            Fabric.with(this, new Crashlytics());
            Timber.plant(new CrashReportingTree());
        }

        // write log file
        if (AppConfig.ENABLE_LOGGER) {
            // Log for troubleshot and testing -> not for production version
            KioskLoggerConfig loggerConfig = getAppLoggerConfig();
            Timber.plant(new DebugReportingTree(new FileLogger(loggerConfig.getFilePath(),
                    loggerConfig.getMaxLogFileSize(), loggerConfig.isShouldWriteLog())));
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            log(priority, tag, message);

            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            } else if (t != null && priority == Log.ERROR) {
                logException(t);
            }
        }
    }

    private class DebugReportingTree extends Timber.DebugTree {

        private FileLogger logger;

        public DebugReportingTree(FileLogger fileLogger) {
            this.logger = fileLogger;
        }

        @Override
        public void i(String message, Object... args) {
            logger.i(message, args != null ? Arrays.toString(args) : "");
        }

        @Override
        public void i(Throwable t, String message, Object... args) {
            logger.i(message, Arrays.toString(args));
        }

        @Override
        public void e(String message, Object... args) {
            logger.e("ERROR: " + message,
                    args != null ? Arrays.toString(args) : ""); // Just add to the log.
        }

        @Override
        public void e(Throwable t, String message, Object... args) {
            logger.e("ERROR: " + message,
                    args != null ? Arrays.toString(args) : ""); // Just add to the log.
        }
    }

    static class ContextSingleton {
        private static MyApplication mInstance;

        private ContextSingleton() {
            // hide public Context Singleton
        }

        public static void setContext(Context context) {
            mInstance = (MyApplication) context;
        }

        public static MyApplication getContext() {
            return mInstance;
        }
    }
}