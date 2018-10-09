package com.sf0404.common.utils;

import android.os.Build;

public class BuildVesrionUtils {

    public static boolean isEqualOrGreater(int versionInt) {
        return Build.VERSION.SDK_INT >= versionInt;
    }
}
