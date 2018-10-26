package com.innovation.rain.feature.collection.signin.presenter;

import com.innovation.rain.app.injection.module.model.AppBus;
import com.innovation.rain.app.injection.module.model.Flow;
import com.innovation.rain.feature.collection.orders.view.OrderListFragment;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInCallback;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInParam;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.innovation.rain.feature.collection.signin.business.usecase.ClientSignInUseCase;
import com.innovation.rain.feature.collection.signin.utils.IdValidation;
import com.innovation.rain.feature.collection.signin.view.ClientSignInView;
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import timber.log.Timber;


public class ClientSignInPresenterImpl extends BasePresenterImpl<ClientSignInView> implements ClientSignInPresenter {

    private final ClientSignInUseCase useCase;
    private final AppBus appBus;

    @Inject
    public ClientSignInPresenterImpl(ClientSignInView view, ClientSignInUseCase useCase, AppBus appBus) {
        super(view);
        this.useCase = useCase;
        this.appBus = appBus;
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
        addDisposable(useCase.setCallback(new ClientSignInCallback(view) {
            @Override
            public void onSuccess(ClientSignInUiModel info) {
                if (appBus.getFlow() == Flow.COLLECT) {
                    view.goToNextScreen(OrderListFragment.class, info);
                } else if (appBus.getFlow() == Flow.SHOP) {
                    view.goToNextScreen(RicaHomeFragment.class, info);
                } else {
                    Timber.i("Undefined flow");
                }
            }
        }).execute(new ClientSignInParam()));
    }

    @Override
    public boolean onEditorAction(@NotNull String str) {
        if (isLengthValid(str)) {
            proceed(str);
        }
        return false;
    }
}
