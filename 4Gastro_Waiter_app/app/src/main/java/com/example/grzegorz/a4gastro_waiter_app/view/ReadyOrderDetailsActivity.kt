package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.viewModel.Order
import com.example.grzegorz.a4gastro_waiter_app.viewModel.ReadyOrderDetailsViewModel
import com.example.grzegorz.a4gastro_waiter_app.viewModel.ReadyOrderDetailsViewModelClass
import kotlinx.android.synthetic.main.activity_ready_order_details.*

class ReadyOrderDetailsActivity : AppCompatActivity() {
    private val viewModel: ReadyOrderDetailsViewModel by lazy { ViewModelProviders.of(this).get(ReadyOrderDetailsViewModelClass::class.java) }
    private val adapter by lazy { OrderListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ready_order_details)

        if(intent.getStringExtra("orderID") != null) {
            viewModel.setOrderDetailID(intent.getStringExtra("orderID"))
        }

        initViewModelSettings()
        orderDetailsRecycleView.adapter = adapter
    }

    private fun initViewModelSettings() {

        viewModel.getOrdersListLiveData().observe(this, Observer(this::updateOrdersList))
    }

    fun cancelButtonClick(v: View) {

        val intent = Intent(this, ReadyOrderListActivity::class.java)
        startActivity(intent)
    }

    fun okButtonClick(v: View) {

        viewModel.removeOrderDetail()

        val intent = Intent(this, ReadyOrderListActivity::class.java)
        startActivity(intent)
    }

    private fun updateOrdersList(orders: List<Order>?) {

        if(orders == null) return

        adapter.items = orders
        adapter.notifyDataSetChanged()
    }
}
