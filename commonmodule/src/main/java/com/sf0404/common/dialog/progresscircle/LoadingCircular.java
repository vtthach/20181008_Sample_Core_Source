package com.sf0404.common.dialog.progresscircle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.sf0404.common.R;


/**
 * The type Lv circular.
 */
public class LoadingCircular extends View {

    private static final int BLUE_30 = 0x3F0000FF;
    private Paint mPaint;

    private float mSize = 0f;
    private float mPadding = 0f;
    private float startAngle = 0f;
    /**
     * The Rect f.
     */
    RectF rectF = new RectF();

    private boolean mAutoStart;
    private float mTrackWidth;
    private float mSweepWidth;
    private int mTrackColor;
    private int mSweepColor;

    /**
     * Instantiates a new Lv circular.
     *
     * @param context the context
     */
    public LoadingCircular(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new Lv circular.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public LoadingCircular(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new Lv circular.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public LoadingCircular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initPaint();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircular);
        mAutoStart = typedArray.getBoolean(R.styleable.LoadingCircular_sf_loading_circular_auto_start, false);
        mTrackWidth = typedArray.getFloat(R.styleable.LoadingCircular_sf_loading_circular_track_width, 5f);
        mSweepWidth = typedArray.getFloat(R.styleable.LoadingCircular_sf_loading_circular_sweep_width, 5f);
        mTrackColor = typedArray.getColor(R.styleable.LoadingCircular_sf_loading_circular_track_color, BLUE_30);
        mSweepColor = typedArray.getColor(R.styleable.LoadingCircular_sf_loading_circular_sweep_color, Color.BLUE);
        typedArray.recycle();
        mPadding = 5;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > h) {
            mSize = h;
        } else {
            mSize = w;
        }
        rectF.set(mPadding, mPadding, mSize - mPadding, mSize - mPadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mTrackColor);
        mPaint.setStrokeWidth(mTrackWidth);
        canvas.drawCircle(mSize / 2, mSize / 2, mSize / 2 - mPadding, mPaint);
        mPaint.setColor(mSweepColor);
        mPaint.setStrokeWidth(mSweepWidth);
        canvas.drawArc(rectF, startAngle, 100, false, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnim();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAutoStart) {
            startAnim();
        }
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Start anim.
     */
    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 1000);
    }

    /**
     * Stop anim.
     */
    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
        }
    }

    /**
     * The Value animator.
     */
    ValueAnimator valueAnimator;

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(valueAnimator -> {
            float value = (float) valueAnimator.getAnimatedValue();
            startAngle = 360 * value;
            invalidate();
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }
        return valueAnimator;
    }

}