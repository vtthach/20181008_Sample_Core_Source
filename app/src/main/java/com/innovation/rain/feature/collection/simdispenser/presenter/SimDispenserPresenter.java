package com.innovation.rain.feature.collection.simdispenser.presenter;

import com.sf0404.core.application.base.presenter.BasePresenter;

import org.jetbrains.annotations.NotNull;


public interface SimDispenserPresenter extends BasePresenter {
    void onTextIdChanged(@NotNull String toString);

    void proceed(String id);

    boolean onEditorAction(@NotNull String toString);
}