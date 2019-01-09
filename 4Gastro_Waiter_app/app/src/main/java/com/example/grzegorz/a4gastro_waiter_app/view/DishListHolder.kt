package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_waiter_app.model.DishData
import kotlinx.android.synthetic.main.dishes_list_item.view.*
import kotlinx.android.synthetic.main.table_list_item.view.*

class DishListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameView = itemView.dishNameTextView
    private val descriptionView = itemView.descriptionTextView
    private val priceView = itemView.priceTextView

    fun bindItem(dish: DishData, onItemClicked: (DishData) -> Unit) {

        nameView.text = dish.name
        descriptionView.text = dish.description
        priceView.text = dish.price.toString()

        itemView.setOnClickListener { onItemClicked(dish) }
    }
}