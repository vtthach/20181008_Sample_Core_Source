package com.sf0404.common.toast.customwindow;

import android.view.View;
import android.widget.TextView;

import com.cbsa.ui.widget.notification.OverlayWindowView;
import com.sf0404.common.R;

class NotificationViewHolder implements OverlayWindowView.OverlayViewHolder<NotificationManager.NotificationData> {

    private final ToastType type;

    TextView tvToastMessage;

    View rootView;

    public NotificationViewHolder(ToastType type) {
        this.type = type;
    }

    @Override
    public int getLayoutId() {
        if (type == ToastType.TYPE_ERROR) {
            return R.layout.view_notify_holder_error;
        } else {
            return R.layout.view_notify_holder_info;
        }
    }

    @Override
    public void initView(View view) {
        rootView = view;
        tvToastMessage =view.findViewById(R.id.tvToastMessage);
    }

    @Override
    public void updateData(NotificationManager.NotificationData o) {
        tvToastMessage.setText(o.msg);
    }

    @Override
    public void setCallback(OverlayWindowView.NotificationCallback notificationCallback) {
        // Stub method
    }

    @Override
    public void setNotificationView(OverlayWindowView overlayWindowView) {
        // Stub method
    }

    // TODO add callback when onDismiss occur to clean data - for ex:unBinder
}
