package com.sf0404.common.toast.customwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.sf0404.common.R;

public class OverlayWindowView<T> extends FrameLayout {

    private final Handler mHandler = new Handler();

    private int styleAnimationEnterResId;
    private int styleAnimationExitResId;
    private int windowGravity;
    private int windowHeight;
    private int windowWidth;
    private long autoDismissDuration;
    private boolean enableAutoDismiss;
    private int marginTop;
    private OverlayViewHolder<T> viewHolder;
    private T data; // NOSONAR -> this field will use later

    private Window mWindow;
    private boolean isAddedToWindow;

    public OverlayWindowView(Context context) {
        super(context);
    }

    private boolean isShowing() {
        return isAddedToWindow;
    }

    public void onUpdateData(T data) {
        this.data = data;
        viewHolder.updateData(data);
        if (isShowing()) {
            resetAutoDismiss();
        } else {
            show();
        }
    }

    public void resetAutoDismiss() {
        if (enableAutoDismiss) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.postDelayed(this::removeFromWindow, autoDismissDuration);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        isAddedToWindow = false;
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onAttachedToWindow() {
        isAddedToWindow = true;
        super.onAttachedToWindow();
        resetAutoDismiss();
    }

    public void removeFromWindow() {
        if (getWindowToken() != null) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), styleAnimationExitResId);
            this.startAnimation(animation);
            ((ViewGroup) mWindow.getDecorView()).removeView(this);
        }
    }

    public void show() {
        if (!isShowing() && isHostActivityRunning()) {
            ((ViewGroup) mWindow.getDecorView()).addView(this, getWindowLayoutParams());
            Animation animation = AnimationUtils.loadAnimation(getContext(), styleAnimationEnterResId);
            this.startAnimation(animation);
        }
    }

    private boolean isHostActivityRunning() {
        Activity activity = null;
        if (getContext() instanceof Activity) {
            activity = (Activity) getContext();
        } else if (getContext() instanceof ContextThemeWrapper) {
            activity = (Activity) ((ContextThemeWrapper) getContext()).getBaseContext();
        }

        return activity != null && !activity.isFinishing();
    }

    private FrameLayout.LayoutParams getWindowLayoutParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(windowWidth, windowHeight, windowGravity);
        params.topMargin = marginTop;
        return params;
    }

    public void dismiss() {
        mHandler.removeCallbacksAndMessages(null);
        removeFromWindow();
    }

    /**
     * BUILDER CLASS
     * Gives us a builder utility class with a fluent API for eaily configuring Notification views
     */
    public static class Builder<T> {
        private Window mWindow;

        private NotificationCallback callback;
        private OverlayViewHolder<T> viewHolder;
        private T viewHolderData;

        // Default values
        private int styleAnimationEnter = R.anim.translate_alpha_enter_top;
        private int styleAnimationExit = R.anim.translate_alpha_exit_top;
        private int windowGravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        private int windowHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        private int windowWidth = WindowManager.LayoutParams.WRAP_CONTENT;
        private long autoDismissDuration = 5000;
        private boolean enableAutoDismiss = true;
        private int marginTop;

        public Builder(Window window) {
            this.mWindow = window;
        }

        public Builder<T> windowGravity(int gravity) {
            this.windowGravity = gravity;
            return this;
        }

        public Builder<T> animationExter(@StyleRes int styleAnimationResId) {
            this.styleAnimationEnter = styleAnimationResId;
            return this;
        }

        public Builder<T> animationExit(@StyleRes int styleAnimationResId) {
            this.styleAnimationExit = styleAnimationResId;
            return this;
        }

        public Builder<T> windowWidth(int windowWidth) {
            this.windowWidth = windowWidth;
            return this;
        }

        public Builder<T> windowHeight(int windowHeight) {
            this.windowHeight = windowHeight;
            return this;
        }

        public Builder<T> autoDismissDuration(long autoDismissDuration) {
            this.enableAutoDismiss = true;
            this.autoDismissDuration = autoDismissDuration;
            return this;
        }

        public Builder<T> enableAutoDismiss(boolean enableAutoDismiss) {
            this.enableAutoDismiss = enableAutoDismiss;
            return this;
        }

        public Builder<T> withData(T data) {
            this.viewHolderData = data;
            return this;
        }

        public OverlayWindowView<T> build() {
            checkException();
            OverlayWindowView<T> notificationView = new OverlayWindowView<>(mWindow.getContext());
            notificationView.mWindow = mWindow;
            notificationView.windowGravity = windowGravity;
            notificationView.styleAnimationEnterResId = styleAnimationEnter;
            notificationView.styleAnimationExitResId = styleAnimationExit;
            notificationView.windowWidth = windowWidth;
            notificationView.windowHeight = windowHeight;
            notificationView.enableAutoDismiss = enableAutoDismiss;
            notificationView.autoDismissDuration = autoDismissDuration;
            notificationView.marginTop = marginTop;
            initViewHolder(notificationView, viewHolder, viewHolderData, callback);
            // Just clean reference callback for this builder -> viewHolder will hold it
            callback = null;
            return notificationView;
        }

        private void initViewHolder(OverlayWindowView<T> notificationView, OverlayViewHolder<T> viewHolder, T data, NotificationCallback callback) {
            LayoutInflater.from(notificationView.getContext()).inflate(viewHolder.getLayoutId(), notificationView);
            viewHolder.initView(notificationView);
            viewHolder.updateData(data);
            viewHolder.setCallback(callback);
            viewHolder.setNotificationView(notificationView);
            notificationView.viewHolder = viewHolder;
        }

        private void checkException() {
            if (mWindow == null) {
                throw new OverlayBuilderException("Context must not null");
            }
            if (viewHolder == null) {
                throw new OverlayBuilderException("View holder must not null");
            }
        }

        public OverlayWindowView<T> show() {
            OverlayWindowView<T> overlayWindowView = build();
            overlayWindowView.show();
            return overlayWindowView;
        }

        public Builder<T> withViewHolder(@NonNull OverlayViewHolder<T> viewHolder) {
            this.viewHolder = viewHolder;
            return this;
        }

        public Builder<T> withCallback(NotificationCallback callback) {
            this.callback = callback;
            return this;
        }

        public Builder<T> withMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        private static class OverlayBuilderException extends RuntimeException {
            public OverlayBuilderException(String message) {
                super(message);
            }
        }
    }


    public interface OverlayViewHolder<T> {
        @LayoutRes
        int getLayoutId();

        /**
         * Notify for this viewHolder to attach to new view
         *
         * @param view
         */
        void initView(View view);

        /**
         * Update current data for next show
         *
         * @param data
         */
        void updateData(T data);

        /**
         * View clicked callback
         *
         * @param callback
         */
        void setCallback(NotificationCallback callback);

        /**
         * Set a new notificationView when viewHolder is single instance
         *
         * @param notificationView
         */
        void setNotificationView(OverlayWindowView<T> notificationView);
    }

    @FunctionalInterface
    public interface NotificationCallback {
        void onViewClicked(View view);
    }
}
