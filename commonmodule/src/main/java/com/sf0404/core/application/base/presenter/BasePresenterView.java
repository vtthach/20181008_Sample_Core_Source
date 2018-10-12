package com.sf0404.core.application.base.presenter;

import android.content.Context;
import android.support.annotation.StringRes;

import com.sf0404.core.application.base.callback.BaseCallbackView;


public interface BasePresenterView extends BaseCallbackView {
    Context getAppContext();

    boolean isAttached();

    void finish();

    String getString(@StringRes int id);
}
