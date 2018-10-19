package com.innovation.rain.feature.rica.poa.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.innovation.rain.R
import java.util.*

/**
 * Created by AnhVu on 17-Oct-2018.
 */
class AddressAdapter : RecyclerView.Adapter<AddressAdapter.AddressVH>() {

    private var data: List<String> = ArrayList()
    private var selectedPosition = -1
    var listener: OnItemClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVH {
        return AddressVH(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.address_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AddressVH, position: Int) {
        holder.bind(data[position], (position == selectedPosition))
        holder.itemView.setOnClickListener {
            selectedPosition = position
            listener?.onItemClicked(position)
            notifyDataSetChanged()
        }
    }

    fun swapData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    class AddressVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String, isSelected: Boolean) = with(itemView) {
            (itemView as? TextView)?.let {
                if (isSelected) {
                    it.setBackgroundResource(R.drawable.address_item_selected)
                    it.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                } else {
                    it.setBackgroundResource(R.drawable.address_item_unselect)
                    it.setTextColor(ContextCompat.getColor(context, R.color.dark_gray))
                }
                it.text = item
            }
        }
    }

    interface OnItemClicked {
        fun onItemClicked(position: Int)
    }
}