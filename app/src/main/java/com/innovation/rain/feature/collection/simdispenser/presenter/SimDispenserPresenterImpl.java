package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.innovation.rain.app.injection.module.model.AppBus;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.rain.carddispenser.CardDispenserController;
import com.rain.carddispenser.DispenseCallback;
import com.rain.carddispenser.model.SimEntity;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class SimDispenserPresenterImpl extends BasePresenterImpl<SimDispenserView> implements SimDispenserPresenter {

    private final DispenseUseCase useCase;
    private final CardDispenserController dispenserController;

    @Inject
    public SimDispenserPresenterImpl(SimDispenserView view, DispenseUseCase useCase, CardDispenserController dispenserController, AppBus appBus) {
        super(view);
        this.useCase = useCase;
        this.dispenserController = dispenserController;
        Disposable disposable = Observable.fromIterable(appBus.getOrderList())
                .map(orderEntity -> new SimEntity(orderEntity.getTitle(), orderEntity.getContent()))
                .toList()
                .subscribe(simEntities -> this.dispenserController.init(simEntities));
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
