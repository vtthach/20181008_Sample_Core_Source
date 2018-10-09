package com.sf0404.common.design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class NavigateStatusBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private int selectedImageId;
    private int resIdFirst;
    private int resIdSecond;

    public NavigateStatusBehavior(Context context, AttributeSet attrs) {
//        resIdFirst = // TODO
//        resIdSecond = // TODO
        selectedImageId = resIdFirst;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        // Note that the RelativeLayout gets translated.
        float y = dependency.getY();
        animateNavigateImage(y, child);
        if (y <= 0 && selectedImageId != resIdFirst) {
            selectedImageId = resIdFirst;
            child.animate().setDuration(300).rotation(180);
        } else if (selectedImageId != resIdSecond) {
            selectedImageId = resIdSecond;
            child.animate().setDuration(300).rotation(0);
        }
        return true;
    }

    private void animateNavigateImage(float bottomSheetY, ImageView imgNavigateStatus) {
        float y = bottomSheetY - imgNavigateStatus.getHeight() / 2;
        imgNavigateStatus.animate().setDuration(0).y(y <= 0 ? 0 : y);
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        child.setTranslationY(0.0f);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        return dependency instanceof NestedScrollView || dependency instanceof RecyclerView;
    }
}