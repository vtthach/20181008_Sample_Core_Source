package com.innovation.rain.feature.collection.signin.presenter;

import com.innovation.rain.feature.collection.signin.business.usecase.ClientSignInUseCase;
import com.innovation.rain.feature.collection.signin.view.ClientSignInView;
import com.sf0404.core.application.base.presenter.BasePresenterImpl;

import javax.inject.Inject;


public class ClientSignInPresenterImpl extends BasePresenterImpl<ClientSignInView> implements ClientSignInPresenter {

    private final ClientSignInUseCase useCase;

    @Inject
    public ClientSignInPresenterImpl(ClientSignInView view, ClientSignInUseCase useCase) {
        super(view);
        this.useCase = useCase;
    }

}
