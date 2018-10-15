package com.sf0404.common.toast.customwindow

import android.view.View
import android.widget.TextView
import butterknife.Unbinder
import com.cbsa.ui.widget.notification.OverlayWindowView
import com.sf0404.common.R
import timber.log.Timber

class NotificationViewHolder(private val type: ToastType) : OverlayWindowView.OverlayViewHolder<NotificationManager.NotificationData> {
    override fun setNotificationView(p0: OverlayWindowView<NotificationManager.NotificationData>?) {
    }

    var tvToastMessage: TextView? = null

    lateinit var rootView: View

    override fun getLayoutId(): Int {
        return if (type == ToastType.TYPE_ERROR) {
            R.layout.view_notify_holder_error
        } else {
            R.layout.view_notify_holder_info
        }
    }

    override fun initView(view: View) {
        Timber.i("vtt InitView")
        rootView = view
        tvToastMessage = view.findViewById(R.id.tvToastMessage)
    }

    override fun updateData(o: NotificationManager.NotificationData) {
        tvToastMessage!!.text = o.msg
    }

    override fun setCallback(notificationCallback: OverlayWindowView.NotificationCallback) {
        // Stub method
    }

    // TODO add callback when onDismiss occur to clean data - for ex:unBinder
}
