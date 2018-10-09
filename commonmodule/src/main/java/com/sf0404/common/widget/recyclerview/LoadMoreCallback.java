package com.sf0404.common.widget.recyclerview;


public interface LoadMoreCallback {

    boolean canLoadMore();

    void startLoad(boolean isReload);
}
