package com.sf0404.common.toast.customwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.cbsa.ui.widget.notification.OverlayWindowView;
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

    protected OverlayWindowView.Builder<NotificationData> getDefaultBuilder(Context context) {
        return new OverlayWindowView.Builder<NotificationData>(context)
                .withData(this.notificationData)
                .windowType(WindowManager.LayoutParams.TYPE_APPLICATION)
                .animationStyle(R.style.AppNotificationAnim)
                .withMarginTop(getDefaultMarginTop(context))
                .withCallback(this)
                .withViewHolder(new NotificationViewHolder(ToastType.TYPE_INFO));
    }

    private int getDefaultMarginTop(Context context) {
        return 10;
    }

    public void showNotifyError(String msg) {
        showNotify(ToastType.TYPE_ERROR, getDefaultMarginTop(activity), msg);
    }

    public void showNotifyError(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_ERROR, viewPosition, errorMessage);
    }

    public void showNotifyInfo(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_INFO, viewPosition, errorMessage);
    }

    public void showNotifyInfo(String errorMessage) {
        showNotify(ToastType.TYPE_INFO, getDefaultMarginTop(activity), errorMessage);
    }

    public void showNotify(ToastType type, int topMargin, String msg) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> {
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            notificationData.msg = msg;
            if (notificationView != null) {
                notificationView.dismiss();
            }
            notificationView = builder
                    .withData(notificationData)
                    .withMarginTop(topMargin)
                    .windowWidth(WindowManager.LayoutParams.MATCH_PARENT)
                    .animationStyle(R.style.AppNotificationAnim)
                    .withViewHolder(new NotificationViewHolder(type))
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
        String msg;
    }
}
