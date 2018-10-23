package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.sf0404.core.application.base.presenter.BasePresenter;


public interface SimDispenserPresenter extends BasePresenter {
    void scanAnotherSim();

    void printSlip();
}