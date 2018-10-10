package com.innovation.rain.feature.agentlogin.view;


import com.innovation.rain.app.base.presenter.BasePresenterView;

public interface AgentLoginView extends BasePresenterView {
    void notifyEmptyPw();

    void notifyEmptyUserId();

    void enableButtonSignIn(boolean allowEnableSignInButton);
}
