package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.DishData

class DishesListAdapter(var items: List<DishData>, private val onItemClicked: (DishData) -> Unit): RecyclerView.Adapter<DishListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dishes_list_item, parent, false)
        return DishListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DishListHolder, position: Int) {
        holder.bindItem(items[position], onItemClicked)
    }
}