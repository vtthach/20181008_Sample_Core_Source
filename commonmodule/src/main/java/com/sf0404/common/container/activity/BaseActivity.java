package com.sf0404.common.container.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.sf0404.common.utils.UiUtil.hideKeyboard;


public class BaseActivity extends AppCompatActivity{

    Unbinder unbinder;

    private final int[] viewLocations = new int[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onInitComponent();
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            onPrepareView();
            unbinder = ButterKnife.bind(this);
        }
    }

    /**
     * Prepare view before start involve butter knife inject
     */
    protected void onPrepareView() {
        // Stub method
    }

    protected void onInitComponent() {
        // Stub method
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean rs = super.dispatchTouchEvent(ev);
        View v = getCurrentFocus();
        if (ev.getAction() == MotionEvent.ACTION_UP
                && v instanceof EditText) {
            v.getLocationOnScreen(viewLocations);
            float x = ev.getRawX() + v.getLeft() - viewLocations[0];
            float y = ev.getRawY() + v.getTop() - viewLocations[1];
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                hideKeyboard(this);
            }
        }
        return rs;
    }
}
