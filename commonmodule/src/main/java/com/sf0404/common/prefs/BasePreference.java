package com.sf0404.common.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class BasePreference {

    private final SharedPreferences mSharedPreferences;

    public BasePreference(String prefsName, Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mSharedPreferences = applicationContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }
}
