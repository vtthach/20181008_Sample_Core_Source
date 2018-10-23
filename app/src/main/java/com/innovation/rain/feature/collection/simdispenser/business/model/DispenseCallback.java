package com.innovation.rain.feature.collection.simdispenser.business.model;


import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.sf0404.core.application.business.core.callback.BaseCallbackImpl;

public abstract class DispenseCallback extends BaseCallbackImpl<SimDispenserView, DispenseUiModel> {
    public DispenseCallback(SimDispenserView view) {
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
