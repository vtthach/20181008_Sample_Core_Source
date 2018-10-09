package com.sf0404.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;


/**
 * Common utils method for activity transition
 */
public class ActivityTransitionUtils {

    private static final int UNUSED_VALUE = -1;

    /**
     * start next activity with with default activity animation
     *
     * @param activity
     * @param intent
     * @param isFinishPreviousActivity
     */
    public static void startActivity(Activity activity, Intent intent, boolean isFinishPreviousActivity) {
        ActivityCompat.startActivity(activity, intent, null);
        if (isFinishPreviousActivity) {
            activity.finish();
        }
    }

    public static void startActivity(Activity activity, Intent intent) {
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void startActivity(Activity activity, Intent intent, int requestCode) {
        if (requestCode != UNUSED_VALUE) {
            ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
        } else {
            ActivityCompat.startActivity(activity, intent, null);
        }
    }

    /**
     * start Activity For Result with default activity animation
     *
     * @param activity
     * @param intent
     * @param isFinishPreviousActivity
     * @param requestCode
     */

    public static void startActivityForResult(Activity activity, Intent intent, boolean isFinishPreviousActivity, int requestCode) {
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
        if (isFinishPreviousActivity) {
            activity.finish();
        }
    }

    /**
     * finish Activity with default animation
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        activity.finish();
    }
}
