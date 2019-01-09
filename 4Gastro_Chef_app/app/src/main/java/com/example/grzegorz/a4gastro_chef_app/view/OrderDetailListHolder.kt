package com.example.grzegorz.a4gastro_chef_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_chef_app.viewModel.Order
import kotlinx.android.synthetic.main.order_item.view.*

class OrderDetailListHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val dishName = itemView.dishNameTextView
    private val number = itemView.numberTextView
    private val description = itemView.descriptionTextView

    fun bindItem(order: Order) {

        dishName.text = order.name
        number.text = order.number.toString()
        description.text = order.description
    }


}
