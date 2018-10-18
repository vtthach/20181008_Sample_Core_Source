package com.sf0404.core.application.base.presenter;

import android.content.Context;
import android.support.annotation.StringRes;

import com.sf0404.core.application.business.core.callback.BaseCallbackView;


public interface BaseView extends BaseCallbackView {
    Context getAppContext();

    boolean isAttached();

    void finish();

    String getString(@StringRes int id);
}
