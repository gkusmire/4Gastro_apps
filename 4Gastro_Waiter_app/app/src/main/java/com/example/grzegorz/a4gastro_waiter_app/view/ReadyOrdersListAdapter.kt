package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.OrderDetailData
import com.example.grzegorz.a4gastro_waiter_app.model.TableData

class ReadyOrdersListAdapter(var items: List<OrderDetailData>, private val onItemClicked: (OrderDetailData) -> Unit): RecyclerView.Adapter<ReadyOrdersListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadyOrdersListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ready_order_list_item, parent, false)
        return ReadyOrdersListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReadyOrdersListHolder, position: Int) {
        holder.bindItem(items[position], onItemClicked)
    }
}
