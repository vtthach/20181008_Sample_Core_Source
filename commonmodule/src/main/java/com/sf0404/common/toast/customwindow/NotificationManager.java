package com.sf0404.common.toast.customwindow;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.WindowManager;

import com.sf0404.common.R;

public class NotificationManager implements OverlayWindowView.NotificationCallback {
    private final Activity activity;
    protected OverlayWindowView.Builder<NotificationData> builder;
    protected OverlayWindowView<NotificationData> notificationView;
    protected NotificationData notificationData = new NotificationData();
    Handler handler = new Handler();

    public NotificationManager(Activity activity) {
        this.activity = activity;
        this.builder = this.getDefaultBuilder(activity);
    }

    protected OverlayWindowView.Builder<NotificationData> getDefaultBuilder(Activity activity) {
        return new OverlayWindowView.Builder<NotificationData>(activity.getWindow())
                .withData(this.notificationData)
                .withMarginTop(getDefaultMarginTop())
                .withCallback(this)
                .withViewHolder(new NotificationViewHolder());
    }

    private int getDefaultMarginTop() {
        return 10;
    }

    public void showNotifyError(String msg) {
        showNotify(ToastType.TYPE_ERROR, getDefaultMarginTop(), msg);
    }

    public void showNotifyError(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_ERROR, viewPosition, errorMessage);
    }

    public void showNotifyInfo(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_INFO, viewPosition, errorMessage);
    }

    public void showNotifyInfo(String errorMessage) {
        showNotify(ToastType.TYPE_INFO, getDefaultMarginTop(), errorMessage);
    }

    public void showNotify(ToastType type, int topMargin, String msg) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> {
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            notificationData.message = msg;
            notificationData.resIcon = type == ToastType.TYPE_ERROR ? R.drawable.ic_error : R.drawable.ic_success;
            if (notificationView != null) {
                notificationView.dismiss();
            }
            notificationView = builder
                    .withData(notificationData)
                    .withMarginTop(topMargin)
                    .windowWidth(WindowManager.LayoutParams.MATCH_PARENT)
                    .withViewHolder(new NotificationViewHolder())
                    .show();
        }, 200);
    }

    public void hideNotify() {
        if (notificationView != null) {
            notificationView.dismiss();
        }
    }

    @Override
    public void onViewClicked(View view) {
        // Use later
    }

    public void stopIfAny() {
        hideNotify();
        handler.removeCallbacksAndMessages(null);
    }

    public class NotificationData {
        @DrawableRes
        public int resIcon = R.drawable.ic_success; // Default
        public String message; // NOSONAR -> just want access this field public
    }
}
