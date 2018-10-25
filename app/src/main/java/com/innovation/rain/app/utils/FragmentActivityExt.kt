package com.innovation.rain.app.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.innovation.rain.app.dialog.ExitDialogFragment
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode


inline fun <reified T : Fragment> FragmentActivity.showFragment(flag: Int? = null) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    flag?.let {
        intentBuilder.setFlag(flag)
    }
    intentBuilder.setFragmentClass(T::class.java)
            .setActionMode(ToolbarMode.NONE)
            .start()
}

inline fun <reified T : Fragment> Fragment.showFragment(flag: Int = ContainerActivity.UNUSED_VALUE,
                                                        requestCode: Int = ContainerActivity.UNUSED_VALUE) {
    ContainerActivity.IntentBuilder(this).run {
        setFragmentClass(T::class.java)
        setFlag(flag)
        setRequestCode(requestCode)
        setActionMode(ToolbarMode.NONE)
        start()
    }
}

fun FragmentManager.showExitDialog(func: (() -> Unit)? = null) {
    ExitDialogFragment().also {
        it.positiveCallback = func
        it.show(this, ExitDialogFragment::class.java.simpleName)
    }
}

fun Activity.openApp(prefixName: String) {
    packageManager?.let { packageManager ->
        packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                ?.filter { it.packageName.startsWith(prefixName) }
                ?.sortedByDescending { it.packageName } //get the latest version
                ?.get(0)
                ?.apply {
                    val intent = packageManager.getLaunchIntentForPackage(this.packageName)
                    intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
    }
}