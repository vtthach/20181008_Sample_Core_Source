package com.innovation.rain.feature.rica.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment;
import com.innovation.rain.app.base.presenter.BasePresenter;
import com.innovation.rain.app.enums.RicaState;
import com.innovation.rain.feature.agentlogin.view.RicaHomeFragment;
import com.innovation.rain.feature.rica.home.callback.RicaStateView;

public abstract class BaseRicaFragment<T extends BasePresenter> extends BasePresenterInjectionFragment<T> implements RicaStateView {

    private RicaState ricaState;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRicaState(RicaState.STATE_PRE_LOADED);
    }

    protected void enableButtonProceed(boolean allowEnableProceedButton) {
        RicaHomeFragment f = (RicaHomeFragment) getParentFragment();
        f.enableButtonProceed(allowEnableProceedButton);
    }

    public RicaState getRicaState() {
        return ricaState;
    }

    public void setRicaState(RicaState ricaState) {
        this.ricaState = ricaState;
        switch (ricaState) {
            case STATE_PRE_LOADED:
                onRicaStatePreLoad();
                break;
            case STATE_LOADED:
                onRicaStateLoaded();
                break;
            case STATE_DONE:
                onRicaStateDone();
                break;
        }
    }
}
