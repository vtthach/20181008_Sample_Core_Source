package com.innovation.rain.feature.agentlogin.presenter;

import com.sf0404.core.application.business.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;


public interface AgentLoginPresenter extends BasePresenter {
    void onTextPasswordChanged(@NotNull String s);

    void onTextUserIdChanged(@NotNull String toString);
}