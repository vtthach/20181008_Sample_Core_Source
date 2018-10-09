package com.sf0404.common.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sf0404.common.R;


public class FragmentUtils {
    private FragmentUtils() {
        // Private cons
    }

    public static <T extends Fragment> String getDefaultFragmentTag(Class<T> clazz) {
        return clazz.getName();
    }

    public static <T extends Fragment> T getFragmentByClassTag(Class<T> clazz, FragmentManager fragmentManager) {
        return (T) fragmentManager.findFragmentByTag(getDefaultFragmentTag(clazz));
    }

    public static void replaceFragment(int containerId, Fragment fragment, FragmentManager fragmentManager) {
        replaceFragment(false, containerId, fragment, fragmentManager, false);
    }

    public static void replaceFragment(boolean addToBackStack,
                                       int containerId,
                                       @NonNull Fragment fragment,
                                       @NonNull FragmentManager fragmentManager, boolean isEnableAnimation) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isEnableAnimation) {
            fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        fragmentTransaction.replace(containerId, fragment, FragmentUtils.getDefaultFragmentTag(fragment.getClass()));
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static Fragment newFragment(Class<? extends Fragment> fragmentClassName, Context context) {
        return Fragment.instantiate(context, fragmentClassName.getName());
    }

    public static Fragment getFragment(Class<? extends Fragment> fragmentClassName,
                                       Context activity,
                                       FragmentManager fragmentManager) {
        Fragment cacheFragment = getFragmentByClassTag(fragmentClassName
                , fragmentManager);
        if (cacheFragment != null) {
            return cacheFragment;
        }
        return newFragment(fragmentClassName, activity);
    }
}
