package com.sf0404.common.widget.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sf0404.common.recycler.adapter.BaseRecyclerViewAdapter;

import java.util.List;

public abstract class LoadMoreAdapter<T> extends BaseRecyclerViewAdapter<T> {

    private static final int VISIBLE_ITEM_THRESHOLD = 5;

    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;

    protected View emptyView;

    public LoadMoreAdapter(RecyclerView recyclerView, Context context, List<T> itemList) {
        super(context, itemList);
        initLoadMoreListener(recyclerView);
    }

    public void setEmptyView(View view) {
        this.emptyView = view;
    }

    @Override
    public int getItemCount() {
        int size = super.getItemCount();
        if (emptyView != null) {
            if (size == 0) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        }
        return size;
    }

    public void setLoaded() {
        setShowFooter(false);
        isLoading = false;
    }

    private void initLoadMoreListener(RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (preConditionToLoadMore(linearLayoutManager.getItemCount()
                            , linearLayoutManager.findLastCompletelyVisibleItemPosition())
                            && isListenerAccept()) {
                        isLoading = true;
                        setShowFooter(true);
                        onLoadMoreListener.onLoadMore();
                    }
                }
            });
        } else {
            throw new RuntimeException("LoadMoreAdapter currently is  not support for this type: " + layoutManager.getClass());
        }
    }

    private boolean isListenerAccept() {
        return onLoadMoreListener != null && onLoadMoreListener.canLoadMore();
    }

    private boolean preConditionToLoadMore(int totalItemCount, int lastVisibleItem) {
        return !isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_ITEM_THRESHOLD);
    }

    private boolean preConditionToLoadMore(int totalItemCount, int[] lastVisibleItem) {
        return !isLoading && totalItemCount <= (lastVisibleItem[0] + VISIBLE_ITEM_THRESHOLD);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();

        boolean canLoadMore();
    }

//    @Override
//    public GenericViewHolder onCreateFooterHolder(ViewGroup parent) {
//        return new LoadingViewHolder(inflateView(parent, R.layout.view_progress_load_more));
//    }
//
//    static class LoadingViewHolder extends GenericViewHolder {
//        private View loadingView;
//
//        public LoadingViewHolder(View itemView) {
//            super(itemView);
//            loadingView = itemView.findViewById(R.id.viewLoading);
//        }
//
//        @Override
//        public void bindItem(int position, Object item) {
//            loadingView.setVisibility(View.VISIBLE);
//        }
//    }

}
