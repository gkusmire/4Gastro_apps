package com.example.grzegorz.a4gastro_chef_app.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.grzegorz.a4gastro_chef_app.R
import com.example.grzegorz.a4gastro_chef_app.viewModel.Order

class OrdersDetailListAdapter(var items: List<Order>): RecyclerView.Adapter<OrderDetailListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderDetailListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderDetailListHolder, position: Int) {
        holder.bindItem(items[position])
    }

}
