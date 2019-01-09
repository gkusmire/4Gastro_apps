package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.FoodType
import com.example.grzegorz.a4gastro_waiter_app.viewModel.NewOrderViewModel
import com.example.grzegorz.a4gastro_waiter_app.viewModel.NewOrderViewModelClass
import com.example.grzegorz.a4gastro_waiter_app.model.OrderData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order
import kotlinx.android.synthetic.main.activity_new_order.*

class NewOrderActivity : AppCompatActivity() {
    private val viewModel: NewOrderViewModelClass by lazy { ViewModelProviders.of(this).get(NewOrderViewModelClass::class.java) }
    private val adapter by lazy { OrderListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        if(intent.hasExtra("tableID")) {
            viewModel.setTableID(intent.getStringExtra("tableID"))
        }
        init()
        listRecycleView.adapter = adapter
    }

    private fun init() {

        bindTableNumberTextView()
        bindRecycleViewList()
    }

    private fun bindTableNumberTextView() {

        viewModel.getTableNumberLiveData().observe(this, Observer(this::updateTableTextView))
    }

    private fun updateTableTextView(str: String?) {

        if(str == null) return

        tableTextView.text = str
    }

    private fun bindRecycleViewList() {

        viewModel.getOrderList().observe(this, Observer(this::updateOrderList))
    }

    fun confirmOrder(v: View) {
        viewModel.confirmOrder()

        val intent = Intent(this, TablesListActivity::class.java)
        startActivity(intent)
    }

    fun addOrderPosition(v: View) {
        val intent = Intent(this, AddDishActivity::class.java)
            .putExtra("foodType", FoodType.DRINK.ordinal)
        startActivity(intent)
    }

    private fun updateOrderList(orderDataList: List<Order>?) {

        if(orderDataList == null) return

        adapter.items = orderDataList
        adapter.notifyDataSetChanged()
    }

    fun cancelOrder(v: View) {
        viewModel.cancelOrder()

        val intent = Intent(this, TablesListActivity::class.java)
        startActivity(intent)
    }
}
