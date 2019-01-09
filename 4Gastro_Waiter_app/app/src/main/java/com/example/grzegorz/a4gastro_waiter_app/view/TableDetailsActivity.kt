package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order
import com.example.grzegorz.a4gastro_waiter_app.viewModel.TableDetailsViewModel
import com.example.grzegorz.a4gastro_waiter_app.viewModel.TableDetailsViewModelClass
import kotlinx.android.synthetic.main.activity_table_order.*

class TableDetailsActivity : AppCompatActivity() {
    private val viewModel: TableDetailsViewModel by lazy { ViewModelProviders.of(this).get(TableDetailsViewModelClass::class.java) }
    private val adapter by lazy { OrderListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_order)

        init()
        tableOrderRecycleView.adapter = adapter
    }

    private fun init() {
        if(intent.getStringExtra("tableID") != null) {
            viewModel.setTableID(intent.getStringExtra("tableID"))
        }
        if(intent.getStringExtra("tableNumber") != null) {
            viewModel.generateNewTableID(intent.getStringExtra("tableNumber"))
        }
        bindTextViewValue()
        bindRecycleViewList()
    }

    private fun bindRecycleViewList() {

        viewModel.getOrderList().observe(this, Observer(this::updateOrderList))
    }

    private fun updateOrderList(orderList: List<Order>?) {

        if(orderList == null) return

        adapter.items = orderList
        adapter.notifyDataSetChanged()
    }

    private fun bindTextViewValue() {

        viewModel.getTableDescription().observe(this, Observer(this::updateTableNumberText))
    }

    private fun updateTableNumberText(str: String?) {

        if(str == null) return

        tableNumberTextView.text = str
    }

    fun printBill(v: View) {
        viewModel.printBill()

        val intent = Intent(this, TablesListActivity::class.java)
        startActivity(intent)
    }

    fun addNewOrder(v: View) {
        val intent = Intent(this, NewOrderActivity::class.java)
            .putExtra("tableID", viewModel.getTableID())
        startActivity(intent)
    }

    fun closeActivity(v: View) {
        val intent = Intent(this, TablesListActivity::class.java)
        startActivity(intent)
    }
}
