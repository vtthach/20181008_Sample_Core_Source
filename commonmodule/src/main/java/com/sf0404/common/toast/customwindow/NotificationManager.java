package com.sf0404.common.toast.customwindow;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;

import com.cbsa.ui.widget.notification.OverlayWindowView;
import com.sf0404.common.R;
import com.sf0404.common.utils.UiUtil;

public class NotificationManager {
    private final Context appContext;
    protected OverlayWindowView.Builder<NotificationData> builder;
    protected OverlayWindowView<NotificationData> notificationView;
    protected NotificationData notificationData = new NotificationData();
    Handler handler = new Handler();

    public NotificationManager(Context appContext) {
        this.appContext = appContext.getApplicationContext();
        this.builder = this.getDefaultBuilder(appContext);
    }

    protected OverlayWindowView.Builder<NotificationData> getDefaultBuilder(Context context) {
        return new OverlayWindowView.Builder<NotificationData>(context)
                .withData(this.notificationData)
                .animationStyle(R.style.AppNotificationAnim)
                .withMarginTop(getDefaultMarginTop(context))
                .withViewHolder(new NotificationViewHolder(ToastType.TYPE_INFO));
    }

    private int getDefaultMarginTop(Context context) {
        return UiUtil.getScreenSize(context)[1] / 7;
    }

    public void showNotifyError(String msg) {
        showNotify(ToastType.TYPE_ERROR, getDefaultMarginTop(appContext), msg);
    }

    public void showNotifyError(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_ERROR, viewPosition, errorMessage);
    }

    public void showNotifyInfo(int viewPosition, String errorMessage) {
        showNotify(ToastType.TYPE_INFO, viewPosition, errorMessage);
    }

    public void showNotifyInfo(String errorMessage) {
        showNotify(ToastType.TYPE_INFO, getDefaultMarginTop(appContext), errorMessage);
    }

    public void showNotify(ToastType type, int topMargin, String msg) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(() -> {
            notificationData.msg = msg;
            if (notificationView != null) {
                notificationView.dismiss();
            }
            notificationView = builder
                    .withData(notificationData)
                    .withMarginTop(topMargin)
                    .windowType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
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

    class NotificationData {
        String msg;
    }
}
