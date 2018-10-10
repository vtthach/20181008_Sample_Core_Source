package com.sf0404.common.dialog;

import android.app.Activity;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.sf0404.common.dialog.materialdialog.BaseMaterialDialog;
import com.sf0404.common.dialog.progresscircle.ProgressCircleDialogWrapper;

public class DialogFactoryImpl implements DialogFactory {

    public static MaterialDialog getInputDialog(Activity activity,
                                                MaterialDialog.SingleButtonCallback positiveCallback,
                                                MaterialDialog.InputCallback inputCallback,
                                                int title,
                                                int positiveText,
                                                int negativeText) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        builder = builder
                .title(title)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .theme(Theme.LIGHT)
                .input("", "", inputCallback);
        if (positiveCallback != null) {
            builder.onPositive(positiveCallback);
        }
        builder.onNegative((dialog, which) -> dialog.dismiss());
        return builder.build();
    }

    @Override
    public BaseMaterialDialog getProgressDialog(Activity activity) {
        return new ProgressCircleDialogWrapper(activity);
    }
}