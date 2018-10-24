package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.rain.carddispenser.CardDispenserController;
import com.rain.carddispenser.DispenseCallback;
import com.rain.carddispenser.model.SimEntity;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class SimDispenserPresenterImpl extends BasePresenterImpl<SimDispenserView> implements SimDispenserPresenter {

    private final DispenseUseCase useCase;
    private final CardDispenserController dispenserController;

    @Inject
    public SimDispenserPresenterImpl(SimDispenserView view, DispenseUseCase useCase, CardDispenserController dispenserController) {
        super(view);
        this.useCase = useCase;
        this.dispenserController = dispenserController;
    }

    @Override
    public void dispensing() {
        dispenserController.dispensing(dispenseCallback);
    }

    private DispenseCallback dispenseCallback = new DispenseCallback() {
        @Override
        public void onDispenseSuccess(@NotNull SimEntity simEntity) {

        }

        @Override
        public void onDispenseFail(@NotNull SimEntity simEntity) {

        }
    };

    @Override
    public void scanAnotherSim() {

    }

    @Override
    public void printSlip() {

    }
}
