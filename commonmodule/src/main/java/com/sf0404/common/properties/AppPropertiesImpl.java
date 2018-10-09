package com.sf0404.common.properties;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import timber.log.Timber;

public class AppPropertiesImpl implements AppProperties {
    private final Context mContext;
    private final String mPropertySdCardFilePath;
    private final String mPropertyAssetPath;

    private Properties mProp = new Properties();

    /**
     * Public constructor for injection purpose
     * When not use injection must change to private and init singleton for this class
     *
     * @param context
     * @param propertySdCardFilePath
     * @param assetPath
     */
    public AppPropertiesImpl(Context context, String propertySdCardFilePath, String assetPath) {
        this.mContext = context;
        this.mPropertySdCardFilePath = propertySdCardFilePath;
        this.mPropertyAssetPath = assetPath;
    }

    @Override
    public void reloadProperties() {
        if (mContext != null) {
            loadFromAssetFile(mContext, mProp);
            loadPropertiesFromFile();
        }
    }

    private void loadFromAssetFile(@NonNull Context context, Properties prop) {
        Timber.i("loadFromAssetFile called");
        try {
            InputStream is = context.getAssets().open(mPropertyAssetPath);
            prop.load(is);
            is.close();
        } catch (IOException e) {
            Timber.e("loadFromAssetFile error: " + e.getMessage());
        }
    }

    private void loadPropertiesFromFile() {
        try {
            File file = new File(mPropertySdCardFilePath);
            if (file.exists()) {
                Timber.i("loadFromFile called: file" + file.getPath());
                InputStream fileStream = new FileInputStream(file);
                mProp.load(fileStream);
                fileStream.close();
            }
        } catch (IOException e) {
            Timber.e("loadFromFile failed :" + e);
        }
    }

    @Override
    public String getProperty(String key, String defValue) {
        return mProp.getProperty(key, defValue);
    }
}
