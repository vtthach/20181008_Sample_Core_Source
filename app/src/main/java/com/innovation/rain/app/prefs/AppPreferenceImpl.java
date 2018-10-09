package com.innovation.rain.app.prefs;

import android.content.Context;

import com.sf0404.common.prefs.AppPreferences;
import com.sf0404.common.prefs.BasePreference;

import javax.inject.Inject;

public class AppPreferenceImpl extends BasePreference implements AppPreferences {

    private static final String PREFS_NAME = "MyApplication";

    @Inject
    public AppPreferenceImpl(Context context) {
        super(PREFS_NAME, context);
    }
}
