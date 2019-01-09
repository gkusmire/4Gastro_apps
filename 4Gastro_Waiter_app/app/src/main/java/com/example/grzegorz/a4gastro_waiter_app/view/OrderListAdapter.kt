package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order

class OrderListAdapter(var items: List<Order>): RecyclerView.Adapter<OrderListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return OrderListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderListHolder, position: Int) {
        holder.bindItem(items[position])
    }

}