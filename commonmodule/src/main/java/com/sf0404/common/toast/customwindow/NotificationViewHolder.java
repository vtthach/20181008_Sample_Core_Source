package com.sf0404.common.toast.customwindow;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sf0404.common.R;

class NotificationViewHolder implements OverlayWindowView.OverlayViewHolder<NotificationManager.NotificationData>, View.OnClickListener {

    OverlayWindowView<NotificationManager.NotificationData> notificationView;

    private TextView tvMessage;
    private ImageView imgIcon;
    private OverlayWindowView.NotificationCallback notificationCallback;

    @Override
    public int getLayoutId() {
        return R.layout.toast_view_notification_with_icon;
    }

    @Override
    public void initView(View view) {
        // Get view
        tvMessage = (TextView) view.findViewById(R.id.cbsa_tv_notify_message);
        imgIcon = (ImageView) view.findViewById(R.id.img_notify_icon);
        View notifyDismiss = view.findViewById(R.id.cbsa_btn_notify_dismiss);
        // Click listener
        notifyDismiss.setOnClickListener(this);
    }

    @Override
    public void updateData(NotificationManager.NotificationData data) {
        tvMessage.setText(data.message);
        if (data.resIcon != 0) {
            imgIcon.setImageResource(data.resIcon);
        }
    }

    @Override
    public void setCallback(OverlayWindowView.NotificationCallback callback) {
        notificationCallback = callback;
    }

    @Override
    public void setNotificationView(OverlayWindowView<NotificationManager.NotificationData> notificationView) {
        this.notificationView = notificationView;
    }

    @Override
    public void onClick(View viewClicked) {
        if (notificationCallback != null) {
            notificationCallback.onViewClicked(viewClicked);
        }
        if (notificationView != null && viewClicked.getId() == R.id.cbsa_btn_notify_dismiss) {
            notificationView.dismiss();
        }
    }
}
