package com.example.grzegorz.a4gastro_chef_app.view

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.grzegorz.a4gastro_chef_app.viewModel.OrdersViewModel
import com.example.grzegorz.a4gastro_chef_app.viewModel.OrdersViewModelClass
import com.example.grzegorz.a4gastro_chef_app.R
import com.example.grzegorz.a4gastro_chef_app.model.OrderDetailData
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity() {

    private val viewModel: OrdersViewModelClass by lazy { ViewModelProviders.of(this).get(OrdersViewModelClass::class.java) }
    private val adapter by lazy { OrdersListAdapter(emptyList(), this::showOrderDetail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        initViewModelSettings()
        ordersRecycleView.adapter = adapter
    }

    private fun initViewModelSettings() {

        viewModel.getOrdersListLiveData().observe(this, Observer(this::updateTableList))
    }

    private fun updateTableList(orders: List<OrderDetailData>?) {

        if(orders == null) return

        adapter.items = orders
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.logout) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return true
        }
        return true
    }

    private fun showOrderDetail(orderDetail: OrderDetailData) {

        val intent = Intent(this, OrderDetailActivity::class.java)
            .putExtra("orderID", orderDetail.uid)
        startActivity(intent)
    }
}
