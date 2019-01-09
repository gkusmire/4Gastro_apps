package com.example.grzegorz.a4gastro_chef_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_chef_app.R
import com.example.grzegorz.a4gastro_chef_app.viewModel.Order
import com.example.grzegorz.a4gastro_chef_app.viewModel.OrderDetailViewModel
import com.example.grzegorz.a4gastro_chef_app.viewModel.OrderDetailViewModelClass
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : AppCompatActivity() {

    private val viewModel: OrderDetailViewModel by lazy { ViewModelProviders.of(this).get(
        OrderDetailViewModelClass::class.java) }
    private val adapter by lazy { OrdersDetailListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        init()
        tableOrderRecycleView.adapter = adapter
    }

    private fun init() {

        if(intent.getStringExtra("orderID") != null) {
            viewModel.setOrderDetailID(intent.getStringExtra("orderID"))
        }
        bindRecycleViewList()
    }

    private fun bindRecycleViewList() {

        viewModel.getOrderListLiveData().observe(this, Observer(this::updateOrderList))
    }

    private fun updateOrderList(orderList: List<Order>?) {

        if(orderList == null) return

        adapter.items = orderList
        adapter.notifyDataSetChanged()
    }

    fun cancelButtonClick(v: View) {

        val intent = Intent(this, OrderListActivity::class.java)
        startActivity(intent)
    }

    fun realiseOrder(v: View) {

        viewModel.realiseOrder()

        cancelButtonClick(v)
    }
}
