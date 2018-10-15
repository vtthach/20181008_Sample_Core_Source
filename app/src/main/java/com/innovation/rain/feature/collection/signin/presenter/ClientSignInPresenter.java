package com.innovation.rain.feature.collection.signin.presenter;

import com.sf0404.core.application.base.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;


public interface ClientSignInPresenter extends BasePresenter {
    void onTextIdChanged(@NotNull String toString);

    void proceed(String id);
}