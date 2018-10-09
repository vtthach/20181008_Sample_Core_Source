package com.sf0404.common.widget.recyclerview.decorator;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sf0404.common.utils.UiUtil;


/**
 * ItemDecoration implementation that applies an inset margin
 * around each child of the RecyclerView. The inset value is controlled
 * by a dimension resource.
 */
public class CustomInsetDecoration extends RecyclerView.ItemDecoration {

    private final Context context;
    private final int mInsets;

    public CustomInsetDecoration(Context context, int insets) {
        this.context = context;
        this.mInsets = insets;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //We can supply forced insets for each item activityView here in the Rect
        boolean isStartGroup = (boolean) view.getTag();
        if (isStartGroup) {
            outRect.set(mInsets, (int) UiUtil.getPixelFromDP(context, 25), mInsets, mInsets);
        } else {
            outRect.set(mInsets, mInsets, mInsets, mInsets);
        }
    }
}
