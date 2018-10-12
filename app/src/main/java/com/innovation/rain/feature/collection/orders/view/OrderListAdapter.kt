package com.innovation.rain.feature.collection.orders.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.innovation.rain.R
import com.innovation.rain.feature.collection.orders.model.OrderEntity
import kotlinx.android.synthetic.main.order_list_item.view.*
import java.util.*
import javax.inject.Inject

/**
 * Created by AnhVu on 12-Oct-2018.
 */
class OrderListAdapter @Inject constructor() : RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>() {

    var data: List<OrderEntity> = ArrayList()

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.order_list_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) = holder.bind(data[position])

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: OrderEntity) = with(itemView) {
            // TODO: Bind the data with View
            itemTitle.text = item.title
            itemContent.text = item.content
            setOnClickListener {
                itemClickListener?.onItemClick(this, adapterPosition)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}