package com.sf0404.common.dialog;

import android.app.Activity;

import com.sf0404.common.dialog.materialdialog.BaseMaterialDialog;

public interface DialogFactory {

    BaseMaterialDialog getProgressDialog(Activity activity);

}