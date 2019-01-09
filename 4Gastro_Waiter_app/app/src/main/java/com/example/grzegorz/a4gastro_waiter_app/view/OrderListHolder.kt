package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order
import kotlinx.android.synthetic.main.order_list_item.view.*

class OrderListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val dishName = itemView.dishNameTextView
    private val number = itemView.numberTextView
    private val price = itemView.priceTextView
    private val description = itemView.descriptionTextView

    fun bindItem(order: Order) {

        dishName.text = order.name
        number.text = order.number.toString()
        price.text = order.price.toString()
        description.text = order.description
    }

}