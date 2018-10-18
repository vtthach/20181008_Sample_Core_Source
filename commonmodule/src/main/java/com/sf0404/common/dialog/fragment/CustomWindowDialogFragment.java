package com.sf0404.common.dialog.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.sf0404.common.R;

public abstract class CustomWindowDialogFragment extends DialogFragment {

    public static final int ANIMATION_NO_CHANGE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        customDialogStyle(dialog);
        return inflater.inflate(getLayoutId(), null);
    }

    protected void customDialogStyle(Dialog dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setWindowParams(dialog.getWindow());
    }

    protected void setWindowParams(Window window) {
        if (window != null) {
            window.setGravity(getGravity());
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            int animStyle = getAnimationStyle();
            if (animStyle != ANIMATION_NO_CHANGE) {
                window.setWindowAnimations(animStyle);
            }
            window.getAttributes().y = getMarginTop();
        }
    }

    protected int getMarginTop() {
        return 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Default dialog layout param will wrap_content
        // So just reset it when fragment start to show when onStart()
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(getWindowWidth(), getWindowHeight());
        }
    }

    protected int getAnimationStyle() {
        return R.style.DialogAnim;
    }

    protected abstract int getLayoutId();

    protected abstract int getWindowHeight();

    protected abstract int getWindowWidth();

    protected abstract int getGravity();
}

