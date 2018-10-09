package com.sf0404.common.widget.recyclerview.superlistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sf0404.common.recycler.adapter.BaseRecyclerViewAdapter;

import java.util.List;

public abstract class SuperListAdapter<T> extends BaseRecyclerViewAdapter<T> {

    protected View emptyView;


    public SuperListAdapter(Context context,
                            List<T> itemList) {
        super(context, itemList);
    }

    @Override
    public GenericViewHolder onCreateFooterHolder(ViewGroup parent) {
        throw new UnsupportedOperationException("Adapter not support footer type");
    }

    @Override
    public GenericViewHolder onCreateHeaderHolder(ViewGroup parent) {
        throw new UnsupportedOperationException("Adapter not support header type");
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


    public void setEmptyView(View view) {
        this.emptyView = view;
    }

    public void changeItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public interface ItemListener<T> {
        void onItemClicked(T item, int position);
    }
}
