package com.sf0404.common.dialog.progresscircle;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.sf0404.common.R;
import com.sf0404.common.dialog.materialdialog.BaseMaterialDialog;

public class ProgressCircleDialogWrapper extends BaseMaterialDialog {
    /**
     * The M loading circular.
     */
    LoadingCircular mLoadingCircular;

    /**
     * content
     */
    TextView mTextContent;

    /**
     * Instantiates a new Progress circle view.
     */
    public ProgressCircleDialogWrapper(Activity activity) {
        super(activity, R.layout.view_circle_loading, true);
        View v = getDialog().getCustomView();
        mLoadingCircular = v.findViewById(R.id.lv_circular);
        mLoadingCircular.setClickable(false);
        mTextContent = v.findViewById(R.id.textContent);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mLoadingCircular.stopAnim();
    }

    @Override
    public void onShow(DialogInterface dialog) {
        super.onShow(dialog);
        mLoadingCircular.startAnim();
    }
}
