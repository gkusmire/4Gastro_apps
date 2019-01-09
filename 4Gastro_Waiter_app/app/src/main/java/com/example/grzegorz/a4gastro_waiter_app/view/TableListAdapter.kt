package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.TableData

class TableListAdapter(var items: List<TableData>, private val onItemClicked: (TableData) -> Unit): RecyclerView.Adapter<TableListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        return TableListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TableListHolder, position: Int) {
        holder.bindItem(items[position], onItemClicked)
    }
}