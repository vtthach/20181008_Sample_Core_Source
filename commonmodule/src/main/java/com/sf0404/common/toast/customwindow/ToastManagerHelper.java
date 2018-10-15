package com.sf0404.common.toast.customwindow;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sf0404.common.utils.UiUtil;


public class ToastManagerHelper {

    private Context context;
    private NotificationManager notificationManager;
    Snackbar mySnackBar;

    public ToastManagerHelper(@NonNull Activity context) {
        this.context = context;
        this.notificationManager = new NotificationManager(context);
        mySnackBar = Snackbar.make(context.findViewById(android.R.id.content),
                "", Snackbar.LENGTH_LONG);
        mySnackBar.setActionTextColor(Color.WHITE);
    }

    public void showToastError(String errorMessage) {
        mySnackBar.setText(errorMessage);
        mySnackBar.show();
    }

    private NotificationManager notificationManager() {
        return notificationManager;
    }

    public void showToastError(View anchorView, String errorMessage) {
        notificationManager().showNotifyError(getViewPosition(anchorView), errorMessage);
    }

    public void showToastInfo(View anchorView, String errorMessage) {
        notificationManager().showNotifyInfo(getViewPosition(anchorView), errorMessage);
    }

    public void showToastError(int marginTop, String errorMessage) {
        notificationManager().showNotifyError(marginTop, errorMessage);
    }

    public void showToastInfo(int marginTop, String message) {
        notificationManager().showNotifyInfo(marginTop, message);
    }

    public void hideToast() {
        notificationManager().hideNotify();
    }

    public int getViewPosition(View anchorView) {
        int[] position = new int[2];
        if (anchorView != null) {
            anchorView.getLocationOnScreen(position);
            position[1] = position[1] + anchorView.getHeight();
        } else {
            position[1] = 70;
        }
        return (int) (position[1] + UiUtil.getPixelFromDP(context, 20));
    }

    public void showToastInfo(String msg) {
        mySnackBar.setText(msg);
        mySnackBar.show();
    }
}
