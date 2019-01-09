package com.example.grzegorz.a4gastro_chef_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_chef_app.model.OrderDetailData
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val startTimeView = itemView.startTextView

    fun bindItem(orderDetail: OrderDetailData, onItemClicked: (OrderDetailData) -> Unit) {

        startTimeView.text = orderDetail.startTime.toString()

        itemView.setOnClickListener { onItemClicked(orderDetail) }
    }
}
