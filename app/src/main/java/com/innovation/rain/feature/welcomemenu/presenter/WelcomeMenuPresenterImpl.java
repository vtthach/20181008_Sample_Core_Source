package com.innovation.rain.feature.welcomemenu.presenter;

import com.sf0404.core.application.base.presenter.BasePresenterImpl;
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuView;

import javax.inject.Inject;


public class WelcomeMenuPresenterImpl extends BasePresenterImpl<WelcomeMenuView> implements WelcomeMenuPresenter {

    @Inject
    public WelcomeMenuPresenterImpl(WelcomeMenuView view) {
        super(view);
    }

}
