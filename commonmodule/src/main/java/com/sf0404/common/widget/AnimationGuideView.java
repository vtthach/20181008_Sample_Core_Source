package com.sf0404.common.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class AnimationGuideView extends ImageView {
    private AnimationDrawable mAnimationDrawable;

    public AnimationGuideView(Context context) {
        super(context);
        this.init(context);
    }

    private void init(Context context) {
        if (this.getVisibility() == VISIBLE) {
            this.toggleAnimation(true);
        }

    }

    private void toggleAnimation(boolean isStart) {
        if (this.mAnimationDrawable != null) {
            if (isStart) {
                if (!this.mAnimationDrawable.isRunning()) {
                    this.mAnimationDrawable.start();
                }
            } else {
                this.mAnimationDrawable.stop();
            }
        }

    }

    public AnimationGuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    public AnimationGuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context);
    }

    @TargetApi(21)
    public AnimationGuideView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init(context);
    }

    public void setBackground(Drawable background) {
        super.setBackground(background);
        if (background instanceof AnimationDrawable) {
            this.mAnimationDrawable = (AnimationDrawable)this.getBackground();
        }

    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.toggleAnimation(false);
    }

    public void show() {
        if (this.getVisibility() != VISIBLE) {
            this.setVisibility(VISIBLE);
        }

        this.toggleAnimation(true);
    }

    public void hide() {
        if (this.getVisibility() != GONE) {
            this.setVisibility(GONE);
        }

        this.toggleAnimation(false);
    }

    public void postHide() {
        this.post(new Runnable() {
            public void run() {
                AnimationGuideView.this.hide();
            }
        });
    }
}