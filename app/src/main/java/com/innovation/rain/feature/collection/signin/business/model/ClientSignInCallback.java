package com.innovation.rain.feature.collection.signin.business.model;


import com.innovation.rain.feature.collection.signin.view.ClientSignInView;
import com.sf0404.core.application.business.core.callback.BaseCallbackImpl;

public abstract class ClientSignInCallback extends BaseCallbackImpl<ClientSignInView, ClientSignInUiModel> {
    public ClientSignInCallback(ClientSignInView view) {
        super(view);
    }

    public void onNoOrder(String apiCode) {
        view.showDialogNoOrder(apiCode);
    }
}
