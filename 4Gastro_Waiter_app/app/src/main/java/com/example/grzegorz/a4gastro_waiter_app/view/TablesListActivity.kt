package com.example.grzegorz.a4gastro_waiter_app.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.grzegorz.a4gastro_waiter_app.R
import com.example.grzegorz.a4gastro_waiter_app.model.TableData
import com.example.grzegorz.a4gastro_waiter_app.viewModel.TableListViewModel
import com.example.grzegorz.a4gastro_waiter_app.viewModel.TableListViewModelClass
import kotlinx.android.synthetic.main.activity_tables_list.*

class TablesListActivity : AppCompatActivity() {
    private val viewModel: TableListViewModel by lazy { ViewModelProviders.of(this).get(TableListViewModelClass::class.java) }
    private val adapter by lazy { TableListAdapter(emptyList(), this::showTableDetails) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables_list)

        initViewModelSettings()
        tableListRecyclerView.adapter = adapter
    }

    private fun initViewModelSettings() {
        viewModel.getTablesList().observe(this, Observer(this::updateTablesList))
    }

    private fun updateTablesList(tableList: List<TableData>?) {
        if(tableList == null) return

        adapter.items = tableList
        adapter.notifyDataSetChanged()
    }

    fun showNewTableView(v: View) {
        val intent = Intent(this, AddTableNumberActivity::class.java)
        startActivity(intent)
    }

    private fun showTableDetails(table: TableData) {

        val intent = Intent(this, TableDetailsActivity::class.java)
            .putExtra("tableID", table.uid)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.readyOrders) {
                val intent = Intent(this, ReadyOrderListActivity::class.java)
                startActivity(intent)
                return true
        }
        if(item.itemId == R.id.logout) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "Wylogowano", Toast.LENGTH_LONG).show()
            return true
        }
        return true
    }
}
