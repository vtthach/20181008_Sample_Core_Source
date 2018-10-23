package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.innovation.rain.feature.collection.signin.utils.IdValidation;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseCallback;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseParam;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class SimDispenserPresenterImpl extends BasePresenterImpl<SimDispenserView> implements SimDispenserPresenter {

    private final DispenseUseCase useCase;

    @Inject
    public SimDispenserPresenterImpl(SimDispenserView view, DispenseUseCase useCase) {
        super(view);
        this.useCase = useCase;
    }

    @Override
    public void onTextIdChanged(@NotNull String toString) {
        view.enableButtonProceed(isLengthValid(toString));
    }

    private boolean isLengthValid(String str) {
        return str.length() == 13;
    }

    @Override
    public void proceed(String id) {
        if (!IdValidation.validateIdNumber(id)) {
            view.notifyIdInvalid();
            return;
        }
        addDisposable(useCase.setCallback(new DispenseCallback(view) {
            @Override
            public void onSuccess(DispenseUiModel info) {
                view.goToCollectionOrder(info);
            }
        }).execute(new DispenseParam()));
    }

    @Override
    public boolean onEditorAction(@NotNull String str) {
        if (isLengthValid(str)) {
            proceed(str);
        }
        return false;
    }
}
