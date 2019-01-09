package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_waiter_app.model.OrderDetailData
import kotlinx.android.synthetic.main.ready_order_list_item.view.*

class ReadyOrdersListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tableNumberView = itemView.tableNumberTextView
    private val startTimeView = itemView.startTimeTextView
    private val finishTimeView = itemView.finishTimeTextVIew

    fun bindItem(orderDetail: OrderDetailData, onItemClicked: (OrderDetailData) -> Unit) {

        tableNumberView.text = orderDetail.tableID
        startTimeView.text = orderDetail.startTime
        finishTimeView.text = orderDetail.finishTime

        itemView.setOnClickListener { onItemClicked(orderDetail) }
    }
}