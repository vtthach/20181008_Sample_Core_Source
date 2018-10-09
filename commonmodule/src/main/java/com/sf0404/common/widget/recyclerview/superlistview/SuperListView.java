//package com.sf0404.common.widget.recyclerview.superlistview;
//
//
//import android.content.Context;
//import android.support.annotation.AttrRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.StyleRes;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import com.igap.core.R;
//import com.igap.core.R2;
//import com.igap.core.common.widget.recyclerview.LoadMoreCallback;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
//import timber.log.Timber;
//
//public class SuperListView<T> extends FrameLayout {
//
//    @BindView(R2.id.recyclerView)
//    protected RecyclerView recyclerView;
//
//    @Nullable
//    @BindView(R2.id.smoothProgressBar)
//    protected SmoothProgressBar progressBar;
//
//    @BindView(R2.id.swipeLayout)
//    protected SwipeRefreshLayout swipeLayout;
//
//    @BindView(R2.id.tvEmpty)
//    protected TextView tvEmpty;
//
//    protected FrameLayout frameContainerListView;
//
//    protected SuperListAdapter<T> listAdapter;
//
//    private Unbinder unbinder;
//
//    private RecyclerView.LayoutManager layoutManager;
//
//    public SuperListView(@NonNull Context context) {
//        super(context);
//        init(context);
//    }
//
//    public SuperListView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
//
//    public SuperListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(context);
//    }
//
//    public SuperListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(context);
//    }
//
//    private void init(Context context) {
//        LayoutInflater.from(context).inflate(getLayoutId(), this, true);
//        frameContainerListView = findViewById(R.id.frameContainerListView);
//        LayoutInflater.from(context).inflate(getEmptyLayoutId(), frameContainerListView, true);
//        unbinder = ButterKnife.bind(this, this);
//        tvEmpty.setText(getEmptyMessage());
//    }
//
//    protected int getLayoutId() {
//        return R.layout.super_list_view;
//    }
//
//    protected void setCustomProgressBar(SmoothProgressBar progressBar) {
//        this.progressBar = progressBar;
//    }
//
//    protected int getEmptyMessage() {
//        return R.string.super_list_empty_msg;
//    }
//
//    protected int getEmptyLayoutId() {
//        return R.layout.super_list_empty_text_view;
//    }
//
//    public void initRecyclerView(@NonNull SuperListAdapter<T> adapter,
//                                 @NonNull List<T> itemList,
//                                 LoadMoreCallback loadMoreCallback,
//                                 RecyclerView.LayoutManager layoutManager) {
//        listAdapter = adapter;
//        listAdapter.changeItemList(itemList);
//        listAdapter.setEmptyView(getEmptyView());
//        this.layoutManager = layoutManager;
//        recyclerView.setLayoutManager(this.layoutManager);
//        recyclerView.setAdapter(listAdapter);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (loadMoreCallback != null) {
//                    if (isScrollToBottom(SuperListView.this.layoutManager.getItemCount()
//                            , getLastVisibleItem())
//                            && loadMoreCallback.canLoadMore()) {
//                        loadMoreCallback.startLoad(false);
//                    }
//                }
//            }
//        });
//        swipeLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.primary));
//        swipeLayout.setOnRefreshListener(() -> loadMoreCallback.startLoad(true));
//        getEmptyView().setVisibility(View.GONE);
//    }
//
//    protected int getLastVisibleItem() {
//        if (layoutManager instanceof LinearLayoutManager) {
//            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//        } else {
//            throw new UnsupportedOperationException("layoutManager must extent from LinearLayoutManager");
//        }
//    }
//
//    public void initRecyclerView(@NonNull SuperListAdapter<T> adapter,
//                                 @NonNull List<T> itemList,
//                                 LoadMoreCallback loadMoreCallback) {
//        initRecyclerView(adapter, itemList, loadMoreCallback, new LinearLayoutManager(getContext()));
//    }
//
//    protected View getEmptyView() {
//        return tvEmpty;
//    }
//
//    private View inflateView(int emptyLayoutId, ViewGroup superListView) {
//        return LayoutInflater.from(getContext()).inflate(emptyLayoutId, superListView);
//    }
//
//    private boolean isScrollToBottom(int totalItemCount, int lastVisibleItem) {
//        return totalItemCount <= (lastVisibleItem + 4);
//    }
//
//    public void onViewDestroy() {
//        if (progressBar != null) {
//            progressBar.progressiveStop();
//        }
//        swipeLayout.setRefreshing(false);
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
//    }
//
//    public void stopLoadingAnimation(boolean isReload) {
//        if (progressBar != null) {
//            hideProgressBar(progressBar);
//            progressBar.progressiveStop();
//        }
//        swipeLayout.setRefreshing(false);
//        listAdapter.notifyDataSetChanged();
//        if (isReload) {
//            layoutManager.scrollToPosition(0);
//        }
//    }
//
//    protected void hideProgressBar(@NonNull SmoothProgressBar progressBar) {
//        progressBar.setVisibility(INVISIBLE);
//    }
//
//    public void notifyNetworkErrorInEmptyView(String debugErrorMessage) {
//        // use debugErrorMessage later
//        Timber.e("ProductListView - Notify network error:" + debugErrorMessage);
//        tvEmpty.setText(R2.string.notify_no_internet_connection);
//        stopLoadingAnimation(false);
//    }
//
//    public void notifyErrorInEmptyView(String debugErrorMessage) {
//        Timber.e("ProductListView - NotifyErrorInEmptyView:" + debugErrorMessage);
//        // Fixme uncomment notifyErrorInEmptyView to show error in empty when server fix the error
////        tvEmpty.setText(debugErrorMessage);
//        stopLoadingAnimation(false);
//    }
//
//    public void notifyStartLoading(boolean isReload) {
//        if (isReload) {
//            swipeLayout.setRefreshing(true);
//        }
//        if (progressBar != null) {
//            progressBar.setVisibility(VISIBLE);
//            progressBar.progressiveStart();
//        }
//    }
//
//    public void scrollToTop() {
//        recyclerView.smoothScrollToPosition(0);
//    }
//
//    public void togglePullRefresh(boolean enable) {
//        swipeLayout.setEnabled(enable);
//    }
//
//    public void notifyLoadDataComplete(boolean isReload) {
//        tvEmpty.setText(getEmptyMessage());
//        stopLoadingAnimation(isReload);
//    }
//
//    public void notifyDataIndexChanged(int position) {
//        listAdapter.notifyItemChanged(position);
//    }
//
//    public void notifyDataIndexChanged() {
//        listAdapter.notifyDataSetChanged();
//    }
//}
