package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.OrderDetailData
import com.example.grzegorz.a4gastro_waiter_app.model.TableData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.ReadyOrdersViewModel
import com.example.grzegorz.a4gastro_waiter_app.viewModel.ReadyOrdersViewModelClass
import kotlinx.android.synthetic.main.activity_ready_order_list.*

class ReadyOrderListActivity : AppCompatActivity() {
    private val viewModel: ReadyOrdersViewModel by lazy { ViewModelProviders.of(this).get(ReadyOrdersViewModelClass::class.java) }
    private val adapter by lazy { ReadyOrdersListAdapter(emptyList(), this::showOrderDetail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ready_order_list)

        initViewModelSettings()
        readyOrdersRecycleView.adapter = adapter
    }

    private fun initViewModelSettings() {

        viewModel.getReadyOrdersListLiveData().observe(this, Observer(this::updateTableList))
    }

    private fun updateTableList(orders: List<OrderDetailData>?) {

        if(orders == null) return

        adapter.items = orders
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ready_orders_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.tableList) {
            val intent = Intent(this, TablesListActivity::class.java)
            startActivity(intent)
            return true
        }
        if(item.itemId == R.id.logout) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return true
        }
        return true
    }

    private fun showOrderDetail(orderDetail: OrderDetailData) {

        val intent = Intent(this, ReadyOrderDetailsActivity::class.java)
            .putExtra("orderID", orderDetail.uid)
        startActivity(intent)
        Toast.makeText(this, "Wylogowano", Toast.LENGTH_LONG).show()
    }
}
