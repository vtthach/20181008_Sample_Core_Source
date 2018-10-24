package com.innovation.rain.feature.collection.simdispenser.business.model;


import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.sf0404.core.application.business.core.callback.BaseCallbackImpl;

public abstract class SimDispenseCallback extends BaseCallbackImpl<SimDispenserView, DispenseUiModel> {
    public SimDispenseCallback(SimDispenserView view) {
        super(view);
    }
}
