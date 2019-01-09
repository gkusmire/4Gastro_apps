package com.example.grzegorz.a4gastro_waiter_app.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.grzegorz.a4gastro_waiter_app.model.TableData
import kotlinx.android.synthetic.main.table_list_item.view.*

class TableListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val numberView = itemView.numberTextView
    private val startTimeView = itemView.startTimeTextView

    fun bindItem(table: TableData, onItemClicked: (TableData) -> Unit) {
        numberView.text = table.description
        startTimeView.text = table.startTime

        itemView.setOnClickListener { onItemClicked(table) }
    }
}