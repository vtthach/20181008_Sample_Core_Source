package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import javax.inject.Inject;


public class SimDispenserPresenterImpl extends BasePresenterImpl<SimDispenserView> implements SimDispenserPresenter {

    private final DispenseUseCase useCase;

    @Inject
    public SimDispenserPresenterImpl(SimDispenserView view, DispenseUseCase useCase) {
        super(view);
        this.useCase = useCase;
    }

    @Override
    public void scanAnotherSim() {

    }

    @Override
    public void printSlip() {

    }
}
