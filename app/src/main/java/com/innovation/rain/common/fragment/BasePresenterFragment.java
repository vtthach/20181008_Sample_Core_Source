package com.innovation.rain.common.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.innovation.rain.R;
import com.innovation.rain.app.base.presenter.BasePresenter;
import com.innovation.rain.app.base.presenter.BasePresenterView;
import com.sf0404.common.container.activity.BaseActivity;

public abstract class BasePresenterFragment<T extends BasePresenter> extends BaseFragment implements BasePresenterView {

    protected abstract T getPresenter();

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().onCreate(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null) {
            getPresenter().onSaveInstanceState(outState);
        }
    }

    @Nullable
    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().onCreateView(view);
        }
        return view;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().onViewCreated(savedInstanceState, getArguments());
        }
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) {
            getPresenter().onDestroyView();
        }
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

    @Override
    public void notifyNetworkError(String msg) {
        showToastError(getString(R.string.notify_no_internet_connection) + "[" + msg + "]");
    }

    @Override
    public void notifyError(String msg) {
        showToastError(msg);
    }

    @Override
    public void toggleProgress(boolean isShow) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).toggleProgressDialog(isShow);
        }
    }

    protected void notifyEditTextError(EditText editText, @StringRes int stringId) {
        editText.requestFocus();
        editText.setError(getSpannableString(stringId, Color.RED));
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            goBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isAttached() {
        return getActivity() != null && !isDetached();
    }

    @Override
    public void notifyTechnicalException(Throwable e) {
        notifyError(e.getMessage());
    }
}
