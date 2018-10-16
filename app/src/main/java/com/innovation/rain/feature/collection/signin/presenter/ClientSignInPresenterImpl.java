package com.innovation.rain.feature.collection.signin.presenter;

import com.innovation.rain.feature.collection.signin.business.model.ClientSignInCallback;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInParam;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.innovation.rain.feature.collection.signin.business.usecase.ClientSignInUseCase;
import com.innovation.rain.feature.collection.signin.utils.IdValidation;
import com.innovation.rain.feature.collection.signin.view.ClientSignInView;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class ClientSignInPresenterImpl extends BasePresenterImpl<ClientSignInView> implements ClientSignInPresenter {

    private final ClientSignInUseCase useCase;

    @Inject
    public ClientSignInPresenterImpl(ClientSignInView view, ClientSignInUseCase useCase) {
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
        addDisposable(useCase.setCallback(new ClientSignInCallback(view) {
            @Override
            public void onSuccess(ClientSignInUiModel info) {
                view.goToCollectionOrder(info);
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
