package com.innovation.rain.feature.collection.signin.view;


import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.sf0404.core.application.base.presenter.BasePresenterView;

public interface ClientSignInView extends BasePresenterView {
    void enableButtonProceed(boolean enable);

    void goToCollectionOrder(ClientSignInUiModel info);

    void notifyIdInvalid();

    void showDialogNoOrder(String apiCode);

    void notifyNonRegisterId(String apiCode);
}
