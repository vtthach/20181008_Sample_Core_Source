package com.innovation.rain.app.base.presenter;

import android.content.Context;
import android.support.annotation.StringRes;

import com.innovation.rain.app.base.api.callback.BaseCallbackView;


public interface BasePresenterView extends BaseCallbackView {
    Context getAppContext();

    boolean isAttached();

    void finish();

    String getString(@StringRes int id);
}
