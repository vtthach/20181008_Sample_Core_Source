package com.innovation.rain.feature.collection.simdispenser.business.model;


import com.innovation.rain.feature.collection.simdispenser.view.DispenseView;
import com.sf0404.core.application.business.core.callback.BaseCallbackImpl;

public abstract class DispenseCallback extends BaseCallbackImpl<DispenseView, DispenseUiModel> {
    public DispenseCallback(DispenseView view) {
        super(view);
    }

    public void onNoOrder(String apiCode) {
        view.showDialogNoOrder(apiCode);
    }

    public void onNonRegisterId(String apiCode) {
        // TODO integrate with real api
        view.notifyNonRegisterId(apiCode);
    }
}
