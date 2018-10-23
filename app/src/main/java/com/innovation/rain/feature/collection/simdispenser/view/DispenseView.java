package com.innovation.rain.feature.collection.simdispenser.view;


import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;
import com.sf0404.core.application.base.presenter.BaseView;

public interface DispenseView extends BaseView {
    void enableButtonProceed(boolean enable);

    void goToCollectionOrder(DispenseUiModel info);

    void notifyIdInvalid();

    void showDialogNoOrder(String apiCode);

    void notifyNonRegisterId(String apiCode);
}
