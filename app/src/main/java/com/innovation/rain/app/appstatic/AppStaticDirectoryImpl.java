package com.innovation.rain.app.appstatic;

import android.content.Context;

import com.innovation.rain.app.constant.CachedPath;
import com.sf0404.common.utils.FileUtils;


public class AppStaticDirectoryImpl implements AppStaticDirectory {

    private Context mContext;

    public AppStaticDirectoryImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void checking() {
        checkAppPropertiesDirectory();
        checkTncDirectory();
    }

    @Override
    public void checkAppPropertiesDirectory() {
        FileUtils.createFolderIfNotExist(CachedPath.PROPERTIES_FOLDER_PATH);
    }

    @Override
    public void checkTncDirectory() {
        FileUtils.createFolderIfNotExist(CachedPath.KIOSK_TNC_FOLDER_PATH);
    }
}
