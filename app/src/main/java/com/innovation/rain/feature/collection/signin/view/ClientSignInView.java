package com.innovation.rain.feature.collection.signin.view;


import android.support.v4.app.Fragment;

import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.sf0404.core.application.base.presenter.BaseView;

public interface ClientSignInView extends BaseView {
    void enableButtonProceed(boolean enable);

    void goToNextScreen(Class<? extends Fragment> name, ClientSignInUiModel info);

    void notifyIdInvalid();

    void showDialogNoOrder(String apiCode);

    void notifyNonRegisterId(String apiCode);
}
