package com.innovation.rain.feature.agentlogin.presenter;

import com.innovation.rain.app.base.presenter.BasePresenterImpl;
import com.innovation.rain.feature.agentlogin.view.AgentLoginView;
import com.sf0404.common.utils.ValidationUtils;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class AgentLoginPresenterImpl extends BasePresenterImpl<AgentLoginView> implements AgentLoginPresenter {

    private String pw;
    private String userId;

    @Inject
    public AgentLoginPresenterImpl(AgentLoginView view) {
        super(view);
    }

    @Override
    public void onTextPasswordChanged(@NotNull String s) {
        pw = s;
        startCheckEnableSignInButton();
    }

    private void startCheckEnableSignInButton() {
        view.enableButtonSignIn(isAllowEnableSignInButton());
    }

    private boolean isAllowEnableSignInButton() {
        if (ValidationUtils.isNullOrEmpty(pw)) {
            return false;
        }
        if (ValidationUtils.isNullOrEmpty(userId)) {
            return false;
        }
        return true;
    }

    @Override
    public void onTextUserIdChanged(@NotNull String s) {
        this.userId = s;
        startCheckEnableSignInButton();
    }
}
