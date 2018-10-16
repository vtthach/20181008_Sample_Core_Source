package com.sf0404.common.fragment.util;

import android.os.Bundle;

public class BundleUtils {

    private static final String KEY_EXTRA_STATUS_CODE = "KEY_EXTRA_STATUS_CODE";

    /**
     * Get Bundle data for showing error screen status code
     *
     * @param statusCode
     * @return
     */
    public static Bundle getStatusCodeBundle(String statusCode) {
        Bundle data = new Bundle();
        data.putString(KEY_EXTRA_STATUS_CODE, statusCode);
        return data;
    }

    /**
     * get status code from bundle to show error.
     *
     * @param bundle
     * @return
     */
    public static String getStatusCode(Bundle bundle) {
        return bundle != null ? bundle.getString(KEY_EXTRA_STATUS_CODE) : "";
    }
}
