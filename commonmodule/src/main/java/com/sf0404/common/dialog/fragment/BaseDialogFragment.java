package com.sf0404.common.dialog.fragment;

import android.view.Gravity;
import android.view.WindowManager;

public abstract class BaseDialogFragment extends CustomWindowDialogFragment {

    @Override
    protected int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected int getWindowHeight() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getWindowWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }
}
