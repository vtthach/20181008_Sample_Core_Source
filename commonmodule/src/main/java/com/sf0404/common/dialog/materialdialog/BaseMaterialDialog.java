package com.sf0404.common.dialog.materialdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sf0404.common.R;

import butterknife.ButterKnife;

public class BaseMaterialDialog implements MaterialDialog.SingleButtonCallback, DialogInterface.OnDismissListener, DialogInterface.OnShowListener {

    private final MaterialDialog mDialog;
    protected Activity mActivity;
    protected StubPositiveNegativeButtonClick positiveNegativeButtonClick;
    private DialogInterface.OnDismissListener outDismissListener;

    public BaseMaterialDialog(Activity activity, int layoutID, boolean isFullScreen) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        if (layoutID != 0) {
            builder.customView(layoutID, false);
        }
        builder.onPositive(this);
        builder.onNegative(this);
        builder.onNeutral(this);
        mDialog = builder.build();
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnDismissListener(this);
        mDialog.setOnShowListener(this);
        Window window = mDialog.getWindow();
        if (isFullScreen && window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.primary));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN); // TODO add new flag for this line
            window.setGravity(Gravity.CENTER);
        }
        mActivity = activity;
        View customView = mDialog.getCustomView();
        ButterKnife.bind(this, customView);
    }

    public BaseMaterialDialog(Activity activity) {
        this(activity, 0, false);
    }


    public MaterialDialog getDialog() {
        return mDialog;
    }


    public void showDialog() {
        if (!mDialog.isShowing() && activityAvailable()) {
            mDialog.show();
        }
    }

    private boolean activityAvailable() {
        return mActivity != null && !mActivity.isFinishing();
    }

    public void hideDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public boolean isShow() {
        return mDialog.isShowing();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (positiveNegativeButtonClick != null) {
            switch (which) {
                case POSITIVE:
                case NEUTRAL:
                    positiveNegativeButtonClick.onPositiveButtonClicked(dialog);
                    break;
                case NEGATIVE:
                    positiveNegativeButtonClick.onNegativeButtonClicked(dialog);
                    break;
            }
        }
    }

    public void setButtonClickListener(ButtonTittleCallback buttonClick) {
        if (buttonClick.posTitle != null) {
            mDialog.setActionButton(DialogAction.POSITIVE, buttonClick.posTitle);
        }
        if (buttonClick.negTittle != null) {
            mDialog.setActionButton(DialogAction.NEGATIVE, buttonClick.negTittle);
        }
    }

    public void setMessage(String msg) {
        mDialog.setContent(msg);
    }

    public void setTittle(String title) {
        title = title != null ? title : "";
        mDialog.setTitle(title);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // Stub method
        if (outDismissListener != null) {
            outDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onShow(DialogInterface dialog) {
        // Stub method
    }

    public void setOnCancelListener(DialogInterface.OnDismissListener outDismissListener) {
        this.outDismissListener = outDismissListener;
    }
}
