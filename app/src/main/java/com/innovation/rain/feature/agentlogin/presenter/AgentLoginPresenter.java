package com.innovation.rain.feature.agentlogin.presenter;

import com.innovation.rain.app.base.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;


public interface AgentLoginPresenter extends BasePresenter {
    void onTextPasswordChanged(@NotNull String s);

    void onTextUserIdChanged(@NotNull String toString);
}