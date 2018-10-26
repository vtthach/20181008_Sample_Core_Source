package com.innovation.rain.feature.collection.simdispenser.presenter;

import android.os.Bundle;

import com.innovation.rain.app.injection.module.model.AppBus;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseParam;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;
import com.innovation.rain.feature.collection.simdispenser.business.model.SimDispenseCallback;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.rain.carddispenser.CardDispenserController;
import com.rain.carddispenser.DispenseCallback;
import com.rain.carddispenser.model.SimEntity;
import com.sf0404.common.rxjava.RxJavaHandler;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


public class SimDispenserPresenterImpl extends BasePresenterImpl<SimDispenserView> implements SimDispenserPresenter {

    private final DispenseUseCase useCase;
    private final CardDispenserController dispenserController;
    private Disposable disposable;

    private DispenseCallback dispenseCallback = new DispenseCallback() {

        @Override
        public void onDispenseSuccess(@NotNull SimEntity simEntity) {
            Timber.i("vtt onDispenseSuccess call api");
            callDispenseApi(simEntity, true, "");
        }

        @Override
        public void onDispenseFail(@NotNull SimEntity simEntity, String errorCode) {
            Timber.i("vtt onDispenseFail call api");
            callDispenseApi(simEntity, false, errorCode);
        }

        @Override
        public void onNoSimInQueue() {
            view.enableScanAnotherSim(false);
        }
    };

    @Override
    public void onViewCreated(Bundle savedInstanceState, Bundle arguments) {
        super.onViewCreated(savedInstanceState, arguments);
        dispenserController.connect();
        if (savedInstanceState == null) {
            view.showViewDispensing();
            addDisposable(RxJavaHandler.runDelayMainThread(result -> dispensing(), 1000));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dispenserController.disconnect();
    }

    @Inject
    public SimDispenserPresenterImpl(AppBus appBus, SimDispenserView view, DispenseUseCase useCase, CardDispenserController dispenserController) {
        super(view);
        this.useCase = useCase;
        this.dispenserController = dispenserController;
        checkAndInitCardDispenser(appBus);
    }

    private void checkAndInitCardDispenser(AppBus appBus) {
        if (appBus.getOrderList() != null && appBus.getOrderList().size() > 0) {
            this.disposable = Observable.fromIterable(appBus.getOrderList())
                    .map(orderEntity -> new SimEntity(orderEntity.getTitle(), orderEntity.getContent(), ""))
                    .toList()
                    .subscribe(this.dispenserController::init);
        } else {
            view.showDialogDispensingFail("");
        }
    }

    @Override
    public void dispensing() {
        dispenserController.dispensing(dispenseCallback);
    }

    @Override
    public void scanAnotherSim() {
        view.showViewDispensing();
        dispensing();
    }

    private void callDispenseApi(SimEntity simEntity, boolean isSuccess, String errorCode) {
        addDisposable(useCase.setCallback(new SimDispenseCallback(view) {
            @Override
            public void onSuccess(DispenseUiModel info) {
                if (isSuccess) {
                    view.showDispensingSuccess(simEntity);
                } else {
                    view.showDialogDispensingFail(errorCode);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showDialogDispensingFail(errorCode);
            }
        }).execute(new DispenseParam()));
    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        dispenserController.destroy();
        super.onDestroy();
    }
}
