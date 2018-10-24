package com.innovation.rain.feature.collection.simdispenser.view;


import com.sf0404.core.application.base.presenter.BaseView;

public interface SimDispenserView extends BaseView {

    void showViewDispensing();

    void showDispensingSuccess();

    void showDialogDispensingFail(String apiCode);

    void enableScanAnotherSim(boolean enable);
}
