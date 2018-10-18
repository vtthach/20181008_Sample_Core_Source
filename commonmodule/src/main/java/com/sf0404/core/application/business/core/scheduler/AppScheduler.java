package com.sf0404.core.application.business.core.scheduler;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppScheduler {
    public static Scheduler mainThread() {
        return getSchedulerProvider().mainThread();
    }

    public static Scheduler io() {
        return getSchedulerProvider().io();
    }

    public static SchedulerProvider getSchedulerProvider() {
        return SchedulerProviderStaticInstance.getSchedulerProviderInstance();
    }

    public static void setSchedulerProvider(SchedulerProvider instance) {
        SchedulerProviderStaticInstance.setSchedulerProviderInstance(instance);
    }

    public interface SchedulerProvider { //NOSONAR
        Scheduler mainThread();

        Scheduler io();
    }

    public static class DefaultSchedulerProvider implements SchedulerProvider {
        @Override
        public Scheduler mainThread() {
            return AndroidSchedulers.mainThread();
        }

        @Override
        public Scheduler io() {
            return Schedulers.io();
        }
    }

    private static class SchedulerProviderStaticInstance {
        private static SchedulerProvider mScheduler;

        private SchedulerProviderStaticInstance() {
            //No implement
        }

        public static SchedulerProvider getSchedulerProviderInstance() {
            if (mScheduler == null) {
                mScheduler = new DefaultSchedulerProvider();
            }
            return mScheduler;
        }

        public static void setSchedulerProviderInstance(SchedulerProvider instance) {
            mScheduler = instance;
        }
    }
}