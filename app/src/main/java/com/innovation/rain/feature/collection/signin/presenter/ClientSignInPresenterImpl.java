package com.innovation.rain.feature.collection.signin.presenter;

import com.innovation.rain.app.base.presenter.BasePresenterImpl;
import com.innovation.rain.feature.collection.signin.view.ClientSignInView;

import javax.inject.Inject;


public class ClientSignInPresenterImpl extends BasePresenterImpl<ClientSignInView> implements ClientSignInPresenter {

    @Inject
    public ClientSignInPresenterImpl(ClientSignInView view) {
        super(view);
    }

}
