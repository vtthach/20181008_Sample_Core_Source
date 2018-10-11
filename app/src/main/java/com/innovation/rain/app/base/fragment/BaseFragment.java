package com.innovation.rain.app.base.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sf0404.common.container.activity.BaseActivity;
import com.sf0404.common.toast.customwindow.ToastManagerHelper;
import com.sf0404.common.utils.SpannableUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = createView(inflater, container);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToast();
    }

    protected void onInitComponent() {
        // Stub method
    }

    protected abstract int getLayoutId();

    protected void showToastError(String errorMessage) {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.showToastError(errorMessage);
        }
    }

    protected void showToastError(View anchor, String errorMessage) {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.showToastError(anchor, errorMessage);
        }
    }

    private ToastManagerHelper getToastHelper() {
        return getActivity() != null ? ((BaseActivity) getActivity()).getToastHelper() : null;
    }

    protected void hideToast() {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.hideToast();
        }
    }

    protected CharSequence getSpannableString(@StringRes int strId, int color) {
        return SpannableUtils.getSpanColor(getString(strId), color);
    }

//    protected void showSnackBarMessage(@StringRes int snackBarMsg) {
//        showSnackBarMessage(getString(snackBarMsg));
//    }

//    protected void showSnackBarMessage(String message) {
//        View view = getView();
//        if (view != null) {
//            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
//        }
//    }

    protected void showToastInfo(String stringExtra) {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.showToastInfo(stringExtra);
        }
    }

    protected void showToastInfo(int marginTop, String stringExtra) {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.showToastInfo(marginTop, stringExtra);
        }
    }

    protected void showToastInfo(View view, String stringExtra) {
        ToastManagerHelper toastHelper = getToastHelper();
        if (toastHelper != null) {
            toastHelper.showToastInfo(view, stringExtra);
        }
    }

    public void finish() {
        Activity act = getActivity();
        if (act != null) {
            act.finish();
        }
    }

    protected void goBack() {
        hideToast();
        getActivity().finish();
    }
}
