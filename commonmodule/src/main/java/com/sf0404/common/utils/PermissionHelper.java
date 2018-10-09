package com.sf0404.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class PermissionHelper {

    private static final int REQUEST_CODE_DRAW_OVER = 1000;

    public static void checkDrawOver(Activity activity) {
        // Request permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkDrawOverlayPermission(activity)) {
            return;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkDrawOverlayPermission(Activity activity) {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(activity)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + activity.getPackageName()));
            /** request permission via start activity for result */
            activity.startActivityForResult(intent, REQUEST_CODE_DRAW_OVER);
            return true;
        } else {
            return false;
        }

    }
}
